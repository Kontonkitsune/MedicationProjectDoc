package com.example.reminder_data_flair

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.util.Calendar

class AddMedicationActivity : AppCompatActivity() {

    private lateinit var etMedicationName: EditText
    private lateinit var timePickerMedication: TimePicker
    private lateinit var etWarningLabelDirections: EditText
    private lateinit var etEmergencyContactNumber: EditText
    private lateinit var etDosageCountPerDay: EditText
    private lateinit var etDosagePerNewBottle: EditText
    private lateinit var cbSunday: CheckBox
    private lateinit var cbMonday: CheckBox
    private lateinit var cbTuesday: CheckBox
    private lateinit var cbWednesday: CheckBox
    private lateinit var cbThursday: CheckBox
    private lateinit var cbFriday: CheckBox
    private lateinit var cbSaturday: CheckBox
    private lateinit var btnSaveMedication: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_medication_temp)

        etMedicationName = findViewById(R.id.etMedicationName)
        timePickerMedication = findViewById(R.id.timePickerMedication)
        etWarningLabelDirections = findViewById(R.id.etWarningLabelDirections)
        etEmergencyContactNumber = findViewById(R.id.etEmergencyContactNumber)
        etDosageCountPerDay = findViewById(R.id.etDosageCountPerDay)
        etDosagePerNewBottle = findViewById(R.id.etDosagePerNewBottle)
        cbSunday = findViewById(R.id.cbSunday)
        cbMonday = findViewById(R.id.cbMonday)
        cbTuesday = findViewById(R.id.cbTuesday)
        cbWednesday = findViewById(R.id.cbWednesday)
        cbThursday = findViewById(R.id.cbThursday)
        cbFriday = findViewById(R.id.cbFriday)
        cbSaturday = findViewById(R.id.cbSaturday)
        btnSaveMedication = findViewById(R.id.btnSaveMedication)
        btnBack = findViewById(R.id.btnBack)

        btnSaveMedication.setOnClickListener {
            saveMedication()
        }

        btnBack.setOnClickListener {
            showBackConfirmationDialog()
        }
    }

    private fun saveMedication() {
        val medicationName = etMedicationName.text.toString().trim()
        val medicationTime = String.format(
            "%02d:%02d",
            timePickerMedication.hour,
            timePickerMedication.minute
        )
        val days = StringBuilder()

        if (cbSunday.isChecked) days.append("S ")
        if (cbMonday.isChecked) days.append("M ")
        if (cbTuesday.isChecked) days.append("T ")
        if (cbWednesday.isChecked) days.append("W ")
        if (cbThursday.isChecked) days.append("R ")
        if (cbFriday.isChecked) days.append("F ")
        if (cbSaturday.isChecked) days.append("A ")

        val dosageCountPerDay = etDosageCountPerDay.text.toString().toIntOrNull() ?: 0
        val dosagePerNewBottle = etDosagePerNewBottle.text.toString().toIntOrNull() ?: 0

        if (medicationName.isEmpty()) {
            Toast.makeText(this, "Medication name cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if (dosageCountPerDay <= 0 || dosagePerNewBottle <= 0) {
            Toast.makeText(this, "Dosage counts must be positive numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val medication = Medication(
            name = medicationName,
            days = days.toString().trim(),
            time = medicationTime,
            warningLabelDirections = etWarningLabelDirections.text.toString().trim(),
            emergencyContactNumber = etEmergencyContactNumber.text.toString().trim(),
            dosageCountPerDay = dosageCountPerDay,
            dosagePerNewBottle = dosagePerNewBottle,
            taken = false,
            currentDosageCount = dosagePerNewBottle // Initialize with dosage per new bottle
        )

        // Log medication details before saving
        Log.d("AddMedicationActivity", "Saving new medication: $medication")

        lifecycleScope.launch {
            try {
                MyApp.database.medicationDao().insertMedication(medication)
                Log.d("AddMedicationActivity", "Medication saved with ID: ${medication.id}")

                setMedicationAlarm(medication)
                sendNotification(medication)
                finish()
            } catch (e: Exception) {
                Log.e("AddMedicationActivity", "Error saving medication: ${e.message}")
                Toast.makeText(this@AddMedicationActivity, "Failed to save medication", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setMedicationAlarm(medication: Medication) {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val notificationIntent = Intent(this, MedicationAlarmReceiver::class.java).apply {
            putExtra("medication_name", medication.name)
        }

        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        val pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, pendingIntentFlags)

        val timeParts = medication.time.split(":")
        val hour = timeParts[0].toInt()
        val minute = timeParts[1].toInt()

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        // Adjust the calendar to the next valid day
        calendar.set(Calendar.DAY_OF_WEEK, findNextValidDay(medication.days, calendar))

        // Check if the scheduled time is in the past for today; if so, move to the next valid week
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
        }

        // Log the adjusted alarm time and other details
        Log.d("AddMedicationActivity", "Alarm set for: ${calendar.time}")

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    private fun findNextValidDay(days: String, calendar: Calendar): Int {
        val daysMap = mapOf(
            'S' to Calendar.SUNDAY,
            'M' to Calendar.MONDAY,
            'T' to Calendar.TUESDAY,
            'W' to Calendar.WEDNESDAY,
            'R' to Calendar.THURSDAY,
            'F' to Calendar.FRIDAY,
            'A' to Calendar.SATURDAY
        )

        val currentDay = calendar.get(Calendar.DAY_OF_WEEK)
        val sortedDays = days.trim().split(" ").mapNotNull { daysMap[it[0]] }.sorted()

        // Find the next valid day or loop back to the first valid day
        for (day in sortedDays) {
            if (day >= currentDay) {
                return day
            }
        }

        // If no valid day is found, return the first valid day in the next week
        return sortedDays.firstOrNull() ?: currentDay
    }

    private fun sendNotification(medication: Medication) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = Notification.Builder(this)
            .setContentTitle("Medication Reminder")
            .setContentText("Time to take your medication: ${medication.name}")
            .setSmallIcon(R.drawable.app_notif_icon)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId("MedicationReminderChannel")
        }

        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun showBackConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm")
        builder.setMessage("Are you sure you want to go back? Any unsaved progress will be lost.")
        builder.setPositiveButton("Yes") { _, _ ->
            finish()
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }
}

package com.example.reminder_data_flair

import android.Manifest
import android.app.AlarmManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var medicationRecyclerView: RecyclerView
    private lateinit var addMedicationButton: Button
    private lateinit var medicationAdapter: MedicationAdapter
    private lateinit var medicationViewModel: MedicationViewModel

    companion object {
        private const val REQUEST_NOTIFICATION_PERMISSION = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Check and request notification and alarm permissions
        checkNotificationAndAlarmPermissions()

        medicationRecyclerView = findViewById(R.id.rvMedicationSchedule)
        addMedicationButton = findViewById(R.id.btnAddMedication)

        // Set up RecyclerView
        val medicationDao = MyApp.database.medicationDao()
        val repository = MedicationRepository(medicationDao)
        medicationViewModel = ViewModelProvider(this, MedicationViewModelFactory(repository)).get(MedicationViewModel::class.java)

        medicationAdapter = MedicationAdapter(this, mutableListOf(), medicationViewModel) // Pass the ViewModel
        medicationRecyclerView.adapter = medicationAdapter
        medicationRecyclerView.layoutManager = LinearLayoutManager(this)

        // Add divider decoration
        medicationRecyclerView.addItemDecoration(DividerItemDecoration(this))

        medicationViewModel.allMedications.observe(this) { medications ->
            medications?.let {
                Log.d("DashboardActivity", "Medications fetched: $it")

                // Sort medications by relevance to the current date and time
                val sortedMedications = sortMedicationsByRelevance(it)

                // Update adapter with sorted list
                medicationAdapter.updateList(sortedMedications)
            }
        }

        addMedicationButton.setOnClickListener {
            val intent = Intent(this, AddMedicationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkNotificationAndAlarmPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request the permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_NOTIFICATION_PERMISSION
                )
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !canScheduleExactAlarms()) {
            // Request permission to schedule exact alarms
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            startActivity(intent)
        }
    }

    private fun canScheduleExactAlarms(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            try {
                val method = AlarmManager::class.java.getMethod("canScheduleExactAlarms")
                method.invoke(alarmManager) as Boolean
            } catch (e: Exception) {
                Log.e("DashboardActivity", "Reflection error: ${e.message}")
                false
            }
        } else {
            true
        }
    }

    // Sorting medications by relevance to current time and date
    private fun sortMedicationsByRelevance(medications: List<Medication>): List<Medication> {
        val currentTime = Calendar.getInstance()

        return medications.sortedWith(compareBy<Medication> { medication ->
            val nextScheduledTime = getNextScheduledTime(medication, currentTime)
            nextScheduledTime?.timeInMillis ?: Long.MAX_VALUE
        })
    }

    // Determine the next scheduled time for the medication
    private fun getNextScheduledTime(medication: Medication, currentTime: Calendar): Calendar? {
        val daysMap = mapOf(
            'S' to Calendar.SUNDAY,
            'M' to Calendar.MONDAY,
            'T' to Calendar.TUESDAY,
            'W' to Calendar.WEDNESDAY,
            'R' to Calendar.THURSDAY,
            'F' to Calendar.FRIDAY,
            'A' to Calendar.SATURDAY
        )

        val sortedDays = medication.days.mapNotNull { daysMap[it] }.sorted()

        // Try to find the next valid time today or in the future
        for (day in sortedDays) {
            val targetTime = Calendar.getInstance()
            targetTime.set(Calendar.DAY_OF_WEEK, day)
            val timeParts = medication.time.split(":")
            val hour = timeParts[0].toInt()
            val minute = timeParts[1].toInt()

            targetTime.set(Calendar.HOUR_OF_DAY, hour)
            targetTime.set(Calendar.MINUTE, minute)
            targetTime.set(Calendar.SECOND, 0)
            targetTime.set(Calendar.MILLISECOND, 0)

            if (targetTime.after(currentTime) || targetTime.equals(currentTime)) {
                return targetTime
            }
        }

        // If no valid time is found today, find the earliest in the week
        for (day in sortedDays) {
            val targetTime = Calendar.getInstance()
            targetTime.set(Calendar.DAY_OF_WEEK, day)
            val timeParts = medication.time.split(":")
            val hour = timeParts[0].toInt()
            val minute = timeParts[1].toInt()

            targetTime.set(Calendar.HOUR_OF_DAY, hour)
            targetTime.set(Calendar.MINUTE, minute)
            targetTime.set(Calendar.SECOND, 0)
            targetTime.set(Calendar.MILLISECOND, 0)

            if (targetTime.before(currentTime)) {
                targetTime.add(Calendar.WEEK_OF_YEAR, 1)
            }

            return targetTime
        }

        return null
    }
}

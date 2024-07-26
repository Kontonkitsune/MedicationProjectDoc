package com.example.reminder_data_flair

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

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
        val medicationName = etMedicationName.text.toString()
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

        val medication = Medication(
            name = medicationName,
            days = days.toString().trim(),
            time = medicationTime,
            warningLabelDirections = etWarningLabelDirections.text.toString(),
            emergencyContactNumber = etEmergencyContactNumber.text.toString(),
            dosageCountPerDay = etDosageCountPerDay.text.toString().toIntOrNull() ?: 0,
            dosagePerNewBottle = etDosagePerNewBottle.text.toString().toIntOrNull() ?: 0,
            taken = false
        )

        lifecycleScope.launch {
            MyApp.database.medicationDao().insertMedication(medication)
            finish()
        }
    }

    private fun showBackConfirmationDialog() {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to go back? Unsaved changes will be lost.")
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}

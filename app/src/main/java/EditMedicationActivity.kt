package com.example.reminder_data_flair

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class EditMedicationActivity : AppCompatActivity() {

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
    private lateinit var btnDeleteMedication: Button
    private lateinit var btnBack: Button

    private var medicationId: Int = 0
    private lateinit var medicationViewModel: MedicationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_medication_activity)

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
        btnDeleteMedication = findViewById(R.id.btnDeleteMedication)
        btnBack = findViewById(R.id.btnBack)

        medicationId = intent.getIntExtra("medicationId", 0)
        val medicationDao = MyApp.database.medicationDao()
        val repository = MedicationRepository(medicationDao)
        medicationViewModel = ViewModelProvider(this, MedicationViewModelFactory(repository)).get(MedicationViewModel::class.java)

        medicationViewModel.allMedications.observe(this) { medications ->
            val medication = medications.find { it.id == medicationId }
            medication?.let {
                etMedicationName.setText(it.name)
                val time = it.time.split(":")
                timePickerMedication.hour = time[0].toInt()
                timePickerMedication.minute = time[1].toInt()
                etWarningLabelDirections.setText(it.warningLabelDirections)
                etEmergencyContactNumber.setText(it.emergencyContactNumber)
                etDosageCountPerDay.setText(it.dosageCountPerDay.toString())
                etDosagePerNewBottle.setText(it.dosagePerNewBottle.toString())

                // Set checkbox states based on stored days
                cbSunday.isChecked = it.days.contains("S")
                cbMonday.isChecked = it.days.contains("M")
                cbTuesday.isChecked = it.days.contains("T")
                cbWednesday.isChecked = it.days.contains("W")
                cbThursday.isChecked = it.days.contains("R")
                cbFriday.isChecked = it.days.contains("F")
                cbSaturday.isChecked = it.days.contains("A")
            }
        }

        btnSaveMedication.setOnClickListener {
            saveMedication()
        }

        btnDeleteMedication.setOnClickListener {
            showDeleteConfirmationDialog()
        }

        btnBack.setOnClickListener {
            showExitConfirmationDialog()
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
            id = medicationId,
            name = medicationName,
            days = days.toString().trim(),
            time = medicationTime,
            warningLabelDirections = etWarningLabelDirections.text.toString(),
            emergencyContactNumber = etEmergencyContactNumber.text.toString(),
            dosageCountPerDay = etDosageCountPerDay.text.toString().toIntOrNull() ?: 0,
            dosagePerNewBottle = etDosagePerNewBottle.text.toString().toIntOrNull() ?: 0,
            taken = false // or the current state if it needs to be preserved
        )

        lifecycleScope.launch {
            medicationViewModel.updateMedication(medication)
            finish() // Go back to the previous screen
        }
    }

    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Confirm Deletion")
            .setMessage("Are you sure you want to delete this medication? This action cannot be undone.")
            .setPositiveButton("Delete") { _, _ ->
                deleteMedication()
            }
            .setNegativeButton("Cancel", null) // Do nothing
            .show()
    }

    private fun deleteMedication() {
        lifecycleScope.launch {
            medicationViewModel.deleteMedication(medicationId)
            finish() // Go back to the previous screen
        }
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Confirm Exit")
            .setMessage("Are you sure you want to go back? Any unsaved progress will be lost.")
            .setPositiveButton("Yes") { _, _ ->
                finish() // Go back to the previous screen
            }
            .setNegativeButton("Cancel", null) // Do nothing
            .show()
    }
}

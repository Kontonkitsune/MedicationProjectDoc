package com.example.reminder_data_flair

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddMedicationActivity : AppCompatActivity() {

    private lateinit var etMedicationName: EditText
    private lateinit var etMedicationTime: EditText
    private lateinit var cbSunday: CheckBox
    private lateinit var cbMonday: CheckBox
    private lateinit var cbTuesday: CheckBox
    private lateinit var cbWednesday: CheckBox
    private lateinit var cbThursday: CheckBox
    private lateinit var cbFriday: CheckBox
    private lateinit var cbSaturday: CheckBox
    private lateinit var btnSaveMedication: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_medication_temp)

        etMedicationName = findViewById(R.id.etMedicationName)
        etMedicationTime = findViewById(R.id.etMedicationTime)
        cbSunday = findViewById(R.id.cbSunday)
        cbMonday = findViewById(R.id.cbMonday)
        cbTuesday = findViewById(R.id.cbTuesday)
        cbWednesday = findViewById(R.id.cbWednesday)
        cbThursday = findViewById(R.id.cbThursday)
        cbFriday = findViewById(R.id.cbFriday)
        cbSaturday = findViewById(R.id.cbSaturday)
        btnSaveMedication = findViewById(R.id.btnSaveMedication)

        btnSaveMedication.setOnClickListener {
            saveMedication()
        }
    }

    private fun saveMedication() {
        val medicationName = etMedicationName.text.toString()
        val medicationTime = etMedicationTime.text.toString()
        val days = StringBuilder()

        // Collect days based on checkbox state
        if (cbSunday.isChecked) days.append("S ")
        if (cbMonday.isChecked) days.append("M ")
        if (cbTuesday.isChecked) days.append("T ")
        if (cbWednesday.isChecked) days.append("W ")
        if (cbThursday.isChecked) days.append("T ")
        if (cbFriday.isChecked) days.append("F ")
        if (cbSaturday.isChecked) days.append("S ")

        val medication = Medication(
            name = medicationName,
            days = days.toString().trim(),
            time = medicationTime,
            taken = false // Default value
        )

        // Insert medication into the database
        lifecycleScope.launch {
            MyApp.database.medicationDao().insertMedication(medication)
            finish() // Close the activity and return to the previous screen
        }
    }
}

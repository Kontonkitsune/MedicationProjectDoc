// AddMedicationActivity.kt
package com.example.reminder_data_flair

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class AddMedicationActivity : AppCompatActivity() {
    private lateinit var medicationViewModel: MedicationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_medication_temp)

        medicationViewModel = ViewModelProvider(this).get(MedicationViewModel::class.java)

        val editTextName = findViewById<EditText>(R.id.etMedicationName)
        val editTextTime = findViewById<EditText>(R.id.etMedicationTime)
        val buttonSave = findViewById<Button>(R.id.btnSaveMedication)

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val time = editTextTime.text.toString()

            if (name.isNotBlank() && time.isNotBlank()) {
                val medication = Medication(name = name, time = time)
                medicationViewModel.insert(medication)

                // Optional: Return to DashboardActivity
                finish() // or navigate back to the dashboard
            }
        }
    }
}

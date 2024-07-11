package com.example.reminder_data_flair // Adjust this package name to match your project structure

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var medicationAdapter: MedicationAdapter
    private val medications = mutableListOf<Medication>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Example data
        medications.add(Medication("Aspirin", "8:00 AM", false))
        medications.add(Medication("Vitamin C", "12:00 PM", true))

        medicationAdapter = MedicationAdapter(this, medications)
        findViewById<ListView>(R.id.lvMedicationSchedule).adapter = medicationAdapter

        findViewById<Button>(R.id.btnAddMedication).setOnClickListener {
            // Navigate to Add Medication page
            val intent = Intent(this, AddMedicationActivity::class.java)
            startActivity(intent)
        }
    }
}

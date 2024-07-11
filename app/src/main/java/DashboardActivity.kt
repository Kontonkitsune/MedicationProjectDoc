package com.example.reminder_data_flair

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var medicationAdapter: MedicationAdapter
    private val medications = mutableListOf<Medication>()

    // Register the Activity Result Launcher
    private val addMedicationLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val name = data?.getStringExtra("MEDICATION_NAME")
            val time = data?.getStringExtra("MEDICATION_TIME")
            if (name != null && time != null) {
                medications.add(Medication(name, time, false))
                medicationAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Example data
        medications.add(Medication("Aspirin", "8:00 AM", false))
        medications.add(Medication("Vitamin C", "12:00 PM", true))

        medicationAdapter = MedicationAdapter(this, medications)
        findViewById<ListView>(R.id.lvMedicationSchedule).adapter = medicationAdapter

        findViewById<Button>(R.id.btnAddMedication).setOnClickListener {
            // Navigate to Add Medication page using the Activity Result Launcher
            val intent = Intent(this, AddMedicationActivity::class.java)
            addMedicationLauncher.launch(intent)
        }
    }
}

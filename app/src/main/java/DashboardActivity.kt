package com.example.reminder_data_flair

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class DashboardActivity : AppCompatActivity() {
    private lateinit var medicationListView: ListView
    private lateinit var addMedicationButton: Button
    private lateinit var medicationAdapter: MedicationAdapter
    private lateinit var medicationViewModel: MedicationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        medicationListView = findViewById(R.id.lvMedicationSchedule)
        addMedicationButton = findViewById(R.id.btnAddMedication)

        medicationAdapter = MedicationAdapter(this, mutableListOf())
        medicationListView.adapter = medicationAdapter

        val medicationDao = MyApp.database.medicationDao()
        val repository = MedicationRepository(medicationDao)
        medicationViewModel = ViewModelProvider(this, MedicationViewModelFactory(repository)).get(MedicationViewModel::class.java)

        medicationViewModel.allMedications.observe(this, Observer { medications ->
            medications?.let {
                Log.d("DashboardActivity", "Medications fetched: $it")
                medicationAdapter.updateList(it)
            }
        })

        addMedicationButton.setOnClickListener {
            val intent = Intent(this, AddMedicationActivity::class.java)
            startActivity(intent)
        }
    }
}

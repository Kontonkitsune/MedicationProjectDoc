// DashboardActivity.kt
package com.example.reminder_data_flair

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class DashboardActivity : AppCompatActivity() {

    private lateinit var medicationRecyclerView: RecyclerView
    private lateinit var addMedicationButton: Button
    private lateinit var medicationAdapter: MedicationAdapter
    private lateinit var medicationViewModel: MedicationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        medicationRecyclerView = findViewById(R.id.rvMedicationSchedule)
        addMedicationButton = findViewById(R.id.btnAddMedication)

        // Set up RecyclerView
        medicationAdapter = MedicationAdapter(this, mutableListOf())
        medicationRecyclerView.adapter = medicationAdapter
        medicationRecyclerView.layoutManager = LinearLayoutManager(this)

        // Add divider decoration
        medicationRecyclerView.addItemDecoration(DividerItemDecoration(this))

        val medicationDao = MyApp.database.medicationDao()
        val repository = MedicationRepository(medicationDao)
        medicationViewModel = ViewModelProvider(this, MedicationViewModelFactory(repository)).get(MedicationViewModel::class.java)

        medicationViewModel.allMedications.observe(this, Observer { medications ->
            medications?.let {
                Log.d("DashboardActivity", "Medications fetched: $it")

                // Sort medications by time
                val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                val sortedMedications = it.sortedBy { medication ->
                    timeFormat.parse(medication.time)
                }

                // Update adapter with sorted list
                medicationAdapter.updateList(sortedMedications)
            }
        })

        addMedicationButton.setOnClickListener {
            val intent = Intent(this, AddMedicationActivity::class.java)
            startActivity(intent)
        }
    }
}

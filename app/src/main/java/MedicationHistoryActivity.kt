package com.example.reminder_data_flair

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MedicationHistoryActivity : AppCompatActivity() {

    private lateinit var medicationHistoryRecyclerView: RecyclerView
    private lateinit var historyAdapter: MedicationHistoryAdapter
    private lateinit var medicationViewModel: MedicationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medication_history)

        Log.d("MedicationHistoryActivity", "onCreate called")

        // Initialize views
        medicationHistoryRecyclerView = findViewById(R.id.rvMedicationHistory)
        val btnBack: Button = findViewById(R.id.btnBack)

        // Set up RecyclerView
        val medicationDao = MyApp.database.medicationDao()
        val repository = MedicationRepository(medicationDao)
        medicationViewModel = ViewModelProvider(this, MedicationViewModelFactory(repository)).get(MedicationViewModel::class.java)

        historyAdapter = MedicationHistoryAdapter()
        medicationHistoryRecyclerView.adapter = historyAdapter
        medicationHistoryRecyclerView.layoutManager = LinearLayoutManager(this)
        Log.d("MedicationHistoryActivity", "RecyclerView setup complete")

        // Observe all medications and their history
        medicationViewModel.allMedications.observe(this) { medications ->
            val historyEntries = mutableSetOf<MedicationHistoryEntry>()

            medications.forEach { medication ->
                Log.d("MedicationHistoryActivity", "Medication data retrieved: ${medication.name}")

                // Convert each timestamp to a formatted date-time string
                val entries = medication.pastTimestamps.mapNotNull { timestamp ->
                    try {
                        val dateTime = Converters().fromTimestampToString(timestamp)
                        Log.d("MedicationHistoryActivity", "Converted timestamp: $timestamp to $dateTime")

                        // Create a new entry with medication ID and timestamp
                        MedicationHistoryEntry(medication.name, dateTime, medication.id, timestamp)
                    } catch (e: Exception) {
                        Log.e("MedicationHistoryActivity", "Failed to convert timestamp: $timestamp", e)
                        null
                    }
                }

                // Add to the history entries set to ensure uniqueness
                historyEntries.addAll(entries)
            }

            // Sort entries by timestamp in descending order
            val sortedEntries = historyEntries.sortedByDescending { it.timestamp }

            // Submit sorted list to the adapter
            historyAdapter.submitList(sortedEntries)
            Log.d("MedicationHistoryActivity", "History entries submitted to adapter")
        }

        // Back button listener
        btnBack.setOnClickListener {
            Log.d("MedicationHistoryActivity", "Back button clicked")
            finish()
        }
    }
}

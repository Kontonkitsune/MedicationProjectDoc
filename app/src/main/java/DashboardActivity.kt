// DashboardActivity.kt
package com.example.reminder_data_flair

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class DashboardActivity : AppCompatActivity() {
    private lateinit var medicationAdapter: MedicationAdapter
    private lateinit var medicationViewModel: MedicationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        medicationViewModel = ViewModelProvider(this).get(MedicationViewModel::class.java)

        val listView = findViewById<ListView>(R.id.lvMedicationSchedule)
        medicationAdapter = MedicationAdapter(this, mutableListOf())
        listView.adapter = medicationAdapter

        medicationViewModel.allMedications.observe(this, Observer { medications ->
            medications?.let {
                medicationAdapter.updateList(it)
            }
        })

        findViewById<Button>(R.id.btnAddMedication).setOnClickListener {
            val intent = Intent(this, AddMedicationActivity::class.java)
            startActivity(intent)
        }
    }
}

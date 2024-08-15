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
import android.Manifest
import android.app.AlarmManager
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.widget.Toast

class DashboardActivity : AppCompatActivity() {

    private lateinit var medicationRecyclerView: RecyclerView
    private lateinit var addMedicationButton: Button
    private lateinit var medicationAdapter: MedicationAdapter
    private lateinit var medicationViewModel: MedicationViewModel

    companion object {
        private const val REQUEST_NOTIFICATION_PERMISSION = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Check and request notification and alarm permissions
        checkNotificationAndAlarmPermissions()

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

    private fun checkNotificationAndAlarmPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request the permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_NOTIFICATION_PERMISSION
                )
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !canScheduleExactAlarms()) {
            // Request permission to schedule exact alarms
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            startActivity(intent)
        }
    }

    private fun canScheduleExactAlarms(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            try {
                val method = AlarmManager::class.java.getMethod("canScheduleExactAlarms")
                method.invoke(alarmManager) as Boolean
            } catch (e: Exception) {
                Log.e("DashboardActivity", "Reflection error: ${e.message}")
                false
            }
        } else {
            true
        }
    }
}

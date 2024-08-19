package com.example.reminder_data_flair

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MedicationViewModel(private val repository: MedicationRepository) : ViewModel() {

    val allMedications: LiveData<List<Medication>> = repository.allMedications

    fun updateMedication(medication: Medication) {
        viewModelScope.launch {
            repository.updateMedication(medication)
        }
    }

    fun deleteMedication(medicationId: Int) {
        viewModelScope.launch {
            repository.deleteMedication(medicationId)
        }
    }

    fun updateMedicationTakenStatus(medicationId: Int, timestamp: Long) {
        viewModelScope.launch {
            // Get the medication directly if repository returns a LiveData<Medication>
            val medicationLiveData = repository.getMedicationById(medicationId)
            medicationLiveData.value?.let { medication ->
                // Update the pastTimestamps list
                val currentTimestamps = medication.pastTimestamps.toMutableList()
                currentTimestamps.add(timestamp)
                medication.pastTimestamps = currentTimestamps

                // Update medication in the repository
                repository.updateMedication(medication)
            }
        }
    }

}

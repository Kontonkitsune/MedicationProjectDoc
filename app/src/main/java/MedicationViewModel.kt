package com.example.reminder_data_flair

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MedicationViewModel(private val repository: MedicationRepository) : ViewModel() {
    val allMedications: LiveData<List<Medication>> = repository.allMedications

    fun insert(medication: Medication) {
        viewModelScope.launch {
            repository.insertMedication(medication)
        }
    }

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
}

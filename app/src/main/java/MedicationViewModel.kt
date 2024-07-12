// MedicationViewModel.kt
package com.example.reminder_data_flair

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MedicationRepository
    val allMedications: LiveData<List<Medication>>

    init {
        val medicationDao = MedicationDatabase.getDatabase(application).medicationDao()
        repository = MedicationRepository(medicationDao)
        allMedications = repository.allMedications
    }

    fun insert(medication: Medication) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(medication)
    }
}

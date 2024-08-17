package com.example.reminder_data_flair

import androidx.lifecycle.LiveData

class MedicationRepository(private val medicationDao: MedicationDao) {
    val allMedications: LiveData<List<Medication>> = medicationDao.getAllMedications()

    suspend fun insertMedication(medication: Medication) {
        medicationDao.insertMedication(medication)
    }

    suspend fun updateMedication(medication: Medication) {
        medicationDao.updateMedication(medication)
    }

    suspend fun deleteMedication(medicationId: Int) {
        medicationDao.deleteMedication(medicationId)
    }
}

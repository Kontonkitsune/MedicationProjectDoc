package com.example.reminder_data_flair

import androidx.lifecycle.LiveData

class MedicationRepository(private val medicationDao: MedicationDao) {

    fun getMedicationById(id: Int): LiveData<Medication> {
        return medicationDao.getMedicationById(id)
    }

    suspend fun updateMedication(medication: Medication) {
        medicationDao.updateMedication(medication)
    }

    suspend fun deleteMedication(medicationId: Int) {
        medicationDao.deleteMedication(medicationId)
    }

    val allMedications: LiveData<List<Medication>> = medicationDao.getAllMedications()
}
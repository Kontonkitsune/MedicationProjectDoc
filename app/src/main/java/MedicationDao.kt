package com.example.reminder_data_flair

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedicationDao {

    @Query("SELECT * FROM medications")
    fun getAllMedications(): LiveData<List<Medication>>

    @Insert
    suspend fun insertMedication(medication: Medication)

    @Update
    suspend fun updateMedication(medication: Medication)

    @Update
    suspend fun updateMedications(medications: List<Medication>)

    @Query("DELETE FROM medications WHERE id = :medicationId")
    suspend fun deleteMedication(medicationId: Int)

    @Query("SELECT * FROM medications WHERE id = :id")
    fun getMedicationById(id: Int): LiveData<Medication>

    @Query("UPDATE medications SET lastTakenDate = :timestamp WHERE id = :medicationId")
    suspend fun updateMedicationTakenStatus(medicationId: Int, timestamp: Long)

    @Query("UPDATE medications SET pastTimestamps = :timestamps WHERE id = :medicationId")
    suspend fun updateMedicationPastTimestamps(medicationId: Int, timestamps: List<Long>)

    @Query("SELECT pastTimestamps FROM medications WHERE id = :medicationId")
    fun getPastTimestampsAsString(medicationId: Int): LiveData<String>
}
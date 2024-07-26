package com.example.reminder_data_flair

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MedicationDao {

    @Query("DELETE FROM medications WHERE id = :medicationId")
    suspend fun deleteMedication(medicationId: Int)

    @Query("SELECT * FROM medications")
    fun getAllMedications(): LiveData<List<Medication>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // For insert with conflict strategy
    suspend fun insertMedication(medication: Medication)

    @Update
    suspend fun updateMedication(medication: Medication)
}


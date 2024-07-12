// MedicationDao.kt
package com.example.reminder_data_flair

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MedicationDao {
    @Query("SELECT * FROM medications")
    fun getAllMedications(): LiveData<List<Medication>>

    @Insert
    suspend fun insertMedication(medication: Medication)

    @Update
    suspend fun updateMedication(medication: Medication)
}

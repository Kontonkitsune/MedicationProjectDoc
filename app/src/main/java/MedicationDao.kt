// MedicationDao.kt
package com.example.reminder_data_flair

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedicationDao {
    @Query("SELECT * FROM medications")
    fun getAllMedications(): LiveData<List<Medication>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedication(medication: Medication)

    @Update
    suspend fun updateMedication(medication: Medication)
}

package com.example.reminder_data_flair

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "medications")
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val days: String,
    val time: String,
    val warningLabelDirections: String,
    val emergencyContactNumber: String,
    val dosageCountPerDay: Int,
    val dosagePerNewBottle: Int,
    var taken: Boolean,
    var lastTakenDate: Long? = initializeDefaultLastTakenDate(),
    var currentDosageCount: Int // Track the current dosage count
) {
    companion object {
        // Function to get the default date for lastTakenDate
        private fun initializeDefaultLastTakenDate(): Long {
            val calendar = Calendar.getInstance().apply {
                set(2000, Calendar.JANUARY, 1, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }
            return calendar.timeInMillis
        }
    }
}

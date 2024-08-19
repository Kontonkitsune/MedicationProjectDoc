package com.example.reminder_data_flair

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
    var currentDosageCount: Int, // Track the current dosage count
    var pastTimestamps: List<Long> = listOf() // Track the history of timestamps when medication was taken
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

// Converter for storing List<Long> as a String in the database
class Converters {

    // Converts a timestamp (Long) to a formatted date-time string
    @TypeConverter
    fun fromTimestampToString(timestamp: Long): String {
        val sdf = SimpleDateFormat("MM/dd/yy h:mm a", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }


    // Converts a list of Longs (timestamps) to a comma-separated string
    @TypeConverter
    fun fromTimestampList(timestamps: List<Long>): String {
        return timestamps.joinToString(",")
    }

    // Converts a comma-separated string back to a list of Longs (timestamps)
    @TypeConverter
    fun toTimestampList(data: String): List<Long> {
        return if (data.isEmpty()) {
            emptyList()
        } else {
            data.split(",").map { it.toLong() }
        }
    }
}
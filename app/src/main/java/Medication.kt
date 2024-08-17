package com.example.reminder_data_flair

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medications")
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Set default value to 0
    val name: String,
    val days: String,
    val time: String,
    val warningLabelDirections: String = "", // New field with default value
    val emergencyContactNumber: String = "", // New field with default value
    val dosageCountPerDay: Int = 0,         // New field with default value
    val dosagePerNewBottle: Int = 0,        // New field with default value
    var taken: Boolean = false              // Default value for taken
)

package com.example.reminder_data_flair

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medications")
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Set default value to 0
    val name: String,
    val days: String,
    val time: String,
    var taken: Boolean
)

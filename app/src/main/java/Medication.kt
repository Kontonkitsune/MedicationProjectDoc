package com.example.reminder_data_flair // Adjust this package name to match your project structure

data class Medication(
    val name: String,
    val time: String,
    var taken: Boolean // Make sure 'taken' is declared as 'var'
)

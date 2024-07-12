// MyApp.kt
package com.example.reminder_data_flair

import android.app.Application
import androidx.room.Room

class MyApp : Application() {
    companion object {
        lateinit var database: MedicationDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            MedicationDatabase::class.java, "medication-db"
        ).build()
    }
}

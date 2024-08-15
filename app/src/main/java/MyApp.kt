package com.example.reminder_data_flair

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.room.Room

class MyApp : Application() {
    companion object {
        lateinit var database: MedicationDatabase
            private set
        const val CHANNEL_ID = "medication_reminder_channel"
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            MedicationDatabase::class.java, "medication_database"
        ).fallbackToDestructiveMigration() // Add this line for development
            .build()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        Log.d("MyApp", "Attempting to create notification channel")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("MyApp", "Device supports notification channels")

            val name = getString(R.string.notification_channel_name)
            val descriptionText = getString(R.string.notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)

            Log.d("MyApp", "Notification channel created: $name")
        } else {
            Log.d("MyApp", "Device does not support notification channels")
        }
    }

}

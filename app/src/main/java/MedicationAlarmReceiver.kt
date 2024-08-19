package com.example.reminder_data_flair

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Notification
import android.app.PendingIntent
import android.util.Log

class MedicationAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("MedicationAlarmReceiver", "Alarm received")

        val medicationName = intent.getStringExtra("medication_name") ?: "Medication"

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Check if the notification channel exists
        val channel = notificationManager.getNotificationChannel(MyApp.CHANNEL_ID)
        if (channel == null) {
            Log.e("MedicationAlarmReceiver", "Notification Channel not found!")
            // Possibly recreate the channel or handle the error
            return
        } else {
            Log.d("MedicationAlarmReceiver", "Notification Channel found: ${channel.name}")
        }

        val notificationIntent = Intent(context, DashboardActivity::class.java)

        // Add FLAG_IMMUTABLE to PendingIntent
        val pendingIntentFlags = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, pendingIntentFlags)

        val notification = Notification.Builder(context, MyApp.CHANNEL_ID)
            .setSmallIcon(R.drawable.app_notif_icon)
            .setContentTitle("Time to take your medication")
            .setContentText("It's time to take your medication: $medicationName")
            .setContentIntent(pendingIntent)
            .setPriority(Notification.PRIORITY_HIGH)
            .build()

        notificationManager.notify(1, notification)
    }
}

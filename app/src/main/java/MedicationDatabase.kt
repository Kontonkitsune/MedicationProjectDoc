package com.example.reminder_data_flair

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Medication::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class) // Registering the Converters class
abstract class MedicationDatabase : RoomDatabase() {

    abstract fun medicationDao(): MedicationDao

    companion object {
        @Volatile
        private var INSTANCE: MedicationDatabase? = null

        fun getDatabase(context: Context): MedicationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedicationDatabase::class.java,
                    "medication_database"
                )
                    .addMigrations(MIGRATION_1_2) // Adding migration script
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Migration script from version 1 to 2
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Migration logic:
                // Ensure the new `pastTimestamps` field is properly added.
                database.execSQL("ALTER TABLE medications ADD COLUMN pastTimestamps TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}

package com.example.reminder_data_flair

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddMedicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_medication_temp)

        val nameEditText = findViewById<EditText>(R.id.etMedicationName)
        val timeEditText = findViewById<EditText>(R.id.etMedicationTime)
        val saveButton = findViewById<Button>(R.id.btnSaveMedication)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val time = timeEditText.text.toString()

            // Create an intent to return the result
            val resultIntent = Intent()
            resultIntent.putExtra("MEDICATION_NAME", name)
            resultIntent.putExtra("MEDICATION_TIME", time)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}

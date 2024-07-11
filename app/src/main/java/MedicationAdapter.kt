package com.example.reminder_data_flair

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView

class MedicationAdapter(context: Context, private val medications: List<Medication>) :
    ArrayAdapter<Medication>(context, 0, medications) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_medication, parent, false)
        val medication = medications[position]

        val nameTextView = view.findViewById<TextView>(R.id.tvMedicationName)
        val timeTextView = view.findViewById<TextView>(R.id.tvMedicationTime)
        val takenCheckBox = view.findViewById<CheckBox>(R.id.cbTaken)

        nameTextView.text = medication.name
        timeTextView.text = medication.time
        takenCheckBox.isChecked = medication.taken

        takenCheckBox.setOnCheckedChangeListener { _, isChecked ->
            medication.taken = isChecked
            // Save the updated status if needed
        }

        return view
    }
}

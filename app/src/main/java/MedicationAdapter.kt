package com.example.reminder_data_flair

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView

class MedicationAdapter(context: Context, private var medications: MutableList<Medication>) :
    ArrayAdapter<Medication>(context, 0, medications) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val medication = getItem(position)!!
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_medication, parent, false)

        val nameTextView: TextView = view.findViewById(R.id.tvMedicationName)
        val timeTextView: TextView = view.findViewById(R.id.tvMedicationTime)
        val takenCheckBox: CheckBox = view.findViewById(R.id.cbMedicationTaken)

        nameTextView.text = medication.name
        timeTextView.text = medication.time
        takenCheckBox.isChecked = medication.taken

        // Default days display
        val defaultDays = "S M T W T F S"
        val days = medication.days

        // Day TextViews
        val daySunday: TextView = view.findViewById(R.id.tvDaySunday)
        val dayMonday: TextView = view.findViewById(R.id.tvDayMonday)
        val dayTuesday: TextView = view.findViewById(R.id.tvDayTuesday)
        val dayWednesday: TextView = view.findViewById(R.id.tvDayWednesday)
        val dayThursday: TextView = view.findViewById(R.id.tvDayThursday)
        val dayFriday: TextView = view.findViewById(R.id.tvDayFriday)
        val daySaturday: TextView = view.findViewById(R.id.tvDaySaturday)

        // Set default days
        daySunday.text = "S"
        dayMonday.text = "M"
        dayTuesday.text = "T"
        dayWednesday.text = "W"
        dayThursday.text = "T"
        dayFriday.text = "F"
        daySaturday.text = "S"

        // Bold and color days based on medication.days
        fun setDayStyle(dayTextView: TextView, isBold: Boolean) {
            if (isBold) {
                dayTextView.setTypeface(null, Typeface.BOLD)
                dayTextView.setTextColor(Color.BLACK)
            } else {
                dayTextView.setTypeface(null, Typeface.NORMAL)
                dayTextView.setTextColor(Color.GRAY) // Or your default color
            }
        }

        setDayStyle(daySunday, days.contains('S'))
        setDayStyle(dayMonday, days.contains('M'))
        setDayStyle(dayTuesday, days.contains('T'))
        setDayStyle(dayWednesday, days.contains('W'))
        setDayStyle(dayThursday, days.contains('R'))
        setDayStyle(dayFriday, days.contains('F'))
        setDayStyle(daySaturday, days.contains('A'))

        takenCheckBox.setOnCheckedChangeListener { _, isChecked ->
            medication.taken = isChecked
            // Update database if needed
        }

        return view
    }

    fun updateList(newMedications: List<Medication>) {
        medications.clear()
        medications.addAll(newMedications)
        notifyDataSetChanged() // Ensure this is called
    }
}

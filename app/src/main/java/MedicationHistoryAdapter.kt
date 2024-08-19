package com.example.reminder_data_flair

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil

class MedicationHistoryAdapter : ListAdapter<MedicationHistoryEntry, MedicationHistoryAdapter.MedicationHistoryViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MedicationHistoryEntry>() {
            override fun areItemsTheSame(oldItem: MedicationHistoryEntry, newItem: MedicationHistoryEntry): Boolean {
                return oldItem.name == newItem.name && oldItem.dateTime == newItem.dateTime
            }

            override fun areContentsTheSame(oldItem: MedicationHistoryEntry, newItem: MedicationHistoryEntry): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationHistoryViewHolder {
        Log.d("MedicationHistoryAdapter", "onCreateViewHolder called")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medication_history, parent, false)
        return MedicationHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicationHistoryViewHolder, position: Int) {
        val currentEntry = getItem(position)
        Log.d("MedicationHistoryAdapter", "onBindViewHolder called for position: $position, entry: ${currentEntry.name}")
        holder.bind(currentEntry)
    }

    inner class MedicationHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val medicationNameTextView: TextView = itemView.findViewById(R.id.medicationNameTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)

        fun bind(entry: MedicationHistoryEntry) {
            Log.d("MedicationHistoryAdapter", "Binding entry: ${entry.name}, DateTime: ${entry.dateTime}")

            medicationNameTextView.text = entry.name

            // Safely split the dateTime string into parts
            val dateTimeParts = entry.dateTime.split(" ")

            if (dateTimeParts.size >= 3) {
                // Correctly assign the date and time parts
                dateTextView.text = dateTimeParts[0]
                timeTextView.text = "${dateTimeParts[1]} ${dateTimeParts[2]}"
                Log.d("MedicationHistoryAdapter", "Successfully bound date: ${dateTimeParts[0]}, time: ${dateTimeParts[1]} ${dateTimeParts[2]}")
            } else {
                dateTextView.text = entry.dateTime // Fall back to displaying the full string
                timeTextView.text = "" // Clear the time text
                Log.e("MedicationHistoryAdapter", "Failed to split dateTime into parts, displaying full string: ${entry.dateTime}")
            }
        }
    }
}

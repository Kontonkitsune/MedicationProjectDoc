package com.example.reminder_data_flair

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class MedicationAdapter(private val context: Context, private var medications: MutableList<Medication>) :
    RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>() {

    private val expandedPositions = mutableSetOf<Int>()

    inner class MedicationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivArrow: ImageView = itemView.findViewById(R.id.ivArrow)
        val llHeader: LinearLayout = itemView.findViewById(R.id.llHeader)
        val llExpandedContent: LinearLayout = itemView.findViewById(R.id.llExpandedContent)
        val nameTextView: TextView = itemView.findViewById(R.id.tvMedicationName)
        val timeTextView: TextView = itemView.findViewById(R.id.tvMedicationTime)
        val takenCheckBox: CheckBox = itemView.findViewById(R.id.cbMedicationTaken)
        val editButton: Button = itemView.findViewById(R.id.btnEditMedication)
        val daySunday: TextView = itemView.findViewById(R.id.tvDaySunday)
        val dayMonday: TextView = itemView.findViewById(R.id.tvDayMonday)
        val dayTuesday: TextView = itemView.findViewById(R.id.tvDayTuesday)
        val dayWednesday: TextView = itemView.findViewById(R.id.tvDayWednesday)
        val dayThursday: TextView = itemView.findViewById(R.id.tvDayThursday)
        val dayFriday: TextView = itemView.findViewById(R.id.tvDayFriday)
        val daySaturday: TextView = itemView.findViewById(R.id.tvDaySaturday)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_medication, parent, false)
        return MedicationViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        val medication = medications[position]
        holder.nameTextView.text = medication.name
        holder.timeTextView.text = formatTime(medication.time)
        holder.takenCheckBox.isChecked = medication.taken

        // Set default days
        holder.daySunday.text = "S"
        holder.dayMonday.text = "M"
        holder.dayTuesday.text = "T"
        holder.dayWednesday.text = "W"
        holder.dayThursday.text = "T"
        holder.dayFriday.text = "F"
        holder.daySaturday.text = "S"

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

        setDayStyle(holder.daySunday, medication.days.contains('S'))
        setDayStyle(holder.dayMonday, medication.days.contains('M'))
        setDayStyle(holder.dayTuesday, medication.days.contains('T'))
        setDayStyle(holder.dayWednesday, medication.days.contains('W'))
        setDayStyle(holder.dayThursday, medication.days.contains('R'))
        setDayStyle(holder.dayFriday, medication.days.contains('F'))
        setDayStyle(holder.daySaturday, medication.days.contains('A'))

        // Handle expand/collapse logic
        val isExpanded = expandedPositions.contains(position)
        holder.llExpandedContent.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.ivArrow.setImageResource(
            if (isExpanded) R.drawable.ic_arrow_down else R.drawable.ic_arrow_right
        )

        holder.llHeader.setOnClickListener {
            if (isExpanded) {
                expandedPositions.remove(position)
            } else {
                expandedPositions.add(position)
            }
            notifyItemChanged(position)
        }

        holder.takenCheckBox.setOnCheckedChangeListener { _, isChecked ->
            medication.taken = isChecked
            // Update database if needed
        }

        holder.editButton.setOnClickListener {
            val intent = Intent(context, EditMedicationActivity::class.java).apply {
                putExtra("medicationId", medication.id)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return medications.size
    }

    fun updateList(newMedications: List<Medication>) {
        medications.clear()
        medications.addAll(newMedications)
        notifyDataSetChanged() // Ensure this is called
    }

    private fun formatTime(time: String): String {
        return try {
            val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val outputFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
            val date = inputFormat.parse(time)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            time // Return as is if there's an error
        }
    }
}

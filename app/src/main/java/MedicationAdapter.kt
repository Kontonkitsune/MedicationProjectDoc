package com.example.reminder_data_flair

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MedicationAdapter(
    private val context: Context,
    private var medications: MutableList<Medication>,
    private val medicationViewModel: MedicationViewModel,
    private val onCheckboxClicked: (Medication) -> Unit
) : RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>() {

    private val expandedPositions = mutableSetOf<Int>()

    inner class MedicationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivArrow: ImageView = itemView.findViewById(R.id.ivArrow)
        val llHeader: LinearLayout = itemView.findViewById(R.id.llHeader)
        val llExpandedContent: LinearLayout = itemView.findViewById(R.id.llExpandedContent)
        val nameTextView: TextView = itemView.findViewById(R.id.tvMedicationName)
        val timeTextView: TextView = itemView.findViewById(R.id.tvMedicationTime)
        val takenCheckBox: CheckBox = itemView.findViewById(R.id.cbMedicationTaken)
        val editButton: Button = itemView.findViewById(R.id.btnEditMedication)
        val dosageCountTextView: TextView = itemView.findViewById(R.id.tvDosageCount)
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

        Log.d("MedicationAdapter", "Initializing medication with ID: ${medication.id}, Dosage Count: ${medication.currentDosageCount}")

        holder.nameTextView.text = medication.name
        holder.timeTextView.text = formatTime(medication.time)

        // Remove any previous listeners before setting the state
        holder.takenCheckBox.setOnCheckedChangeListener(null)
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
                dayTextView.setTextColor(Color.GRAY)
            }
        }

        setDayStyle(holder.daySunday, medication.days.contains('S'))
        setDayStyle(holder.dayMonday, medication.days.contains('M'))
        setDayStyle(holder.dayTuesday, medication.days.contains('T'))
        setDayStyle(holder.dayWednesday, medication.days.contains('W'))
        setDayStyle(holder.dayThursday, medication.days.contains('R'))
        setDayStyle(holder.dayFriday, medication.days.contains('F'))
        setDayStyle(holder.daySaturday, medication.days.contains('A'))

        // Handle dosage count display
        holder.dosageCountTextView.text = "Dosage Count: ${medication.currentDosageCount}"
        Log.d("MedicationAdapter", "Displaying medication with ID: ${medication.id}, Dosage Count: ${medication.currentDosageCount}")

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

        // Handle CheckBox state and changes
        holder.takenCheckBox.setOnCheckedChangeListener { _, isChecked ->
            Log.d("MedicationAdapter", "Checkbox checked state for ID: ${medication.id} is: $isChecked")

            medication.taken = isChecked

            // Update medication details if checked
            if (isChecked) {
                val currentDate = Calendar.getInstance().timeInMillis
                medication.lastTakenDate = currentDate

                // Decrement dosage count
                medication.currentDosageCount -= 1
                if (medication.currentDosageCount < 0) medication.currentDosageCount = 0

                holder.dosageCountTextView.text = "Dosage Count: ${medication.currentDosageCount}"

                // Save medication name and current timestamp
                saveMedicationTimestamp(medication, currentDate)

                // Add the current timestamp to the pastTimestamps list
                medication.pastTimestamps = medication.pastTimestamps + currentDate

                // Trigger the lambda function
                onCheckboxClicked(medication)

                // Delayed check to ensure the state is correct after a UI update
                Handler(Looper.getMainLooper()).postDelayed({
                    holder.takenCheckBox.isChecked = medication.taken
                }, 200)
            }

            medicationViewModel.updateMedication(medication)
            Log.d("MedicationAdapter", "Updated medication in database")
        }

        holder.editButton.setOnClickListener {
            val intent = Intent(context, EditMedicationActivity::class.java).apply {
                putExtra("medicationId", medication.id)
            }
            context.startActivity(intent)
        }

        // Schedule the checkbox to be unchecked at 11:59 pm the night before the next scheduled day
        scheduleUncheck(holder, medication)
    }

    override fun getItemCount(): Int {
        return medications.size
    }

    fun updateList(newMedications: List<Medication>) {
        medications.clear()
        medications.addAll(newMedications)
        notifyDataSetChanged()
    }

    private fun formatTime(time: String): String {
        return try {
            val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val outputFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
            val date = inputFormat.parse(time)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            time
        }
    }

    private fun scheduleUncheck(holder: MedicationViewHolder, medication: Medication) {
        val nextScheduledDay = getNextScheduledDay(medication)
        if (nextScheduledDay != null) {
            val calendar = Calendar.getInstance().apply {
                timeInMillis = nextScheduledDay
                add(Calendar.DAY_OF_YEAR, -1) // Go to the previous day
                set(Calendar.HOUR_OF_DAY, 23)
                set(Calendar.MINUTE, 59)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            val delay = calendar.timeInMillis - System.currentTimeMillis()

            Log.d("MedicationAdapter", "Scheduling uncheck for medication ID: ${medication.id} at ${calendar.time}")

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                medication.taken = false
                medicationViewModel.updateMedication(medication)

                // Uncheck the checkbox in the UI
                holder.takenCheckBox.isChecked = false
                Log.d("MedicationAdapter", "Medication ID: ${medication.id} unchecked at ${System.currentTimeMillis()}")
            }, delay)
        }
    }

    private fun getNextScheduledDay(medication: Medication): Long? {
        val currentDate = Calendar.getInstance()

        val daysMap = mapOf(
            'S' to Calendar.SUNDAY,
            'M' to Calendar.MONDAY,
            'T' to Calendar.TUESDAY,
            'W' to Calendar.WEDNESDAY,
            'R' to Calendar.THURSDAY,
            'F' to Calendar.FRIDAY,
            'A' to Calendar.SATURDAY
        )

        val sortedDays = medication.days.mapNotNull { daysMap[it] }.sorted()

        for (day in sortedDays) {
            val targetDate = currentDate.clone() as Calendar
            targetDate.set(Calendar.DAY_OF_WEEK, day)
            if (targetDate.after(currentDate)) {
                return targetDate.timeInMillis
            }
        }

        // If no days this week, set to the first day next week
        if (sortedDays.isNotEmpty()) {
            val targetDate = currentDate.clone() as Calendar
            targetDate.add(Calendar.WEEK_OF_YEAR, 1)
            targetDate.set(Calendar.DAY_OF_WEEK, sortedDays.first())
            return targetDate.timeInMillis
        }

        return null
    }

    // Function to save medication name and timestamp to the database
    private fun saveMedicationTimestamp(medication: Medication, timestamp: Long) {
        // Log the current timestamps for debugging
        Log.d("MedicationAdapter", "Current pastTimestamps for medication ID: ${medication.id} before update: ${medication.pastTimestamps.joinToString(",")}")

        // Add the new timestamp to the pastTimestamps list if it doesn't already exist
        val updatedTimestamps = medication.pastTimestamps.toMutableList()
        if (!updatedTimestamps.contains(timestamp)) {
            updatedTimestamps.add(timestamp)
        }

        // Update the medication's pastTimestamps with the new list
        medication.pastTimestamps = updatedTimestamps

        // Log the updated timestamps for debugging
        Log.d("MedicationAdapter", "Updated pastTimestamps for medication ID: ${medication.id}: ${updatedTimestamps.joinToString(",")}")

        // Save the updated medication object
        medicationViewModel.updateMedication(medication)
        Log.d("MedicationAdapter", "Saved updated medication with new timestamp for medication ID: ${medication.id}")
    }


}


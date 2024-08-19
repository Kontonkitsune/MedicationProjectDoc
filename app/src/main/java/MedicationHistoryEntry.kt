package com.example.reminder_data_flair

data class MedicationHistoryEntry(
    val name: String,
    val dateTime: String,
    val id: Int,
    val timestamp: Long
) {
    // Ensure uniqueness based on ID and timestamp
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MedicationHistoryEntry

        if (id != other.id) return false
        if (timestamp != other.timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + timestamp.hashCode()
        return result
    }
}

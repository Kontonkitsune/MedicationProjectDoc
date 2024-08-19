//[app](../../../index.md)/[com.example.reminder_data_flair](../index.md)/[MedicationDao](index.md)

# MedicationDao

[androidJvm]\
interface [MedicationDao](index.md)

## Functions

| Name | Summary |
|---|---|
| [deleteMedication](delete-medication.md) | [androidJvm]<br>abstract suspend fun [deleteMedication](delete-medication.md)(medicationId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [getAllMedications](get-all-medications.md) | [androidJvm]<br>abstract fun [getAllMedications](get-all-medications.md)(): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Medication](../-medication/index.md)&gt;&gt; |
| [getMedicationById](get-medication-by-id.md) | [androidJvm]<br>abstract fun [getMedicationById](get-medication-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[Medication](../-medication/index.md)&gt; |
| [getPastTimestampsAsString](get-past-timestamps-as-string.md) | [androidJvm]<br>abstract fun [getPastTimestampsAsString](get-past-timestamps-as-string.md)(medicationId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [insertMedication](insert-medication.md) | [androidJvm]<br>abstract suspend fun [insertMedication](insert-medication.md)(medication: [Medication](../-medication/index.md)) |
| [updateMedication](update-medication.md) | [androidJvm]<br>abstract suspend fun [updateMedication](update-medication.md)(medication: [Medication](../-medication/index.md)) |
| [updateMedicationPastTimestamps](update-medication-past-timestamps.md) | [androidJvm]<br>abstract suspend fun [updateMedicationPastTimestamps](update-medication-past-timestamps.md)(medicationId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), timestamps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)&gt;) |
| [updateMedications](update-medications.md) | [androidJvm]<br>abstract suspend fun [updateMedications](update-medications.md)(medications: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Medication](../-medication/index.md)&gt;) |
| [updateMedicationTakenStatus](update-medication-taken-status.md) | [androidJvm]<br>abstract suspend fun [updateMedicationTakenStatus](update-medication-taken-status.md)(medicationId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), timestamp: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) |

//[app](../../../index.md)/[com.example.reminder_data_flair](../index.md)/[MedicationRepository](index.md)

# MedicationRepository

[androidJvm]\
class [MedicationRepository](index.md)(medicationDao: [MedicationDao](../-medication-dao/index.md))

## Constructors

| | |
|---|---|
| [MedicationRepository](-medication-repository.md) | [androidJvm]<br>constructor(medicationDao: [MedicationDao](../-medication-dao/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [allMedications](all-medications.md) | [androidJvm]<br>val [allMedications](all-medications.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Medication](../-medication/index.md)&gt;&gt; |

## Functions

| Name | Summary |
|---|---|
| [deleteMedication](delete-medication.md) | [androidJvm]<br>suspend fun [deleteMedication](delete-medication.md)(medicationId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [getMedicationById](get-medication-by-id.md) | [androidJvm]<br>fun [getMedicationById](get-medication-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[Medication](../-medication/index.md)&gt; |
| [updateMedication](update-medication.md) | [androidJvm]<br>suspend fun [updateMedication](update-medication.md)(medication: [Medication](../-medication/index.md)) |

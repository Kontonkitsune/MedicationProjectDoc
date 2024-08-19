//[app](../../../index.md)/[com.example.reminder_data_flair](../index.md)/[MedicationViewModel](index.md)

# MedicationViewModel

[androidJvm]\
class [MedicationViewModel](index.md)(repository: [MedicationRepository](../-medication-repository/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

## Constructors

| | |
|---|---|
| [MedicationViewModel](-medication-view-model.md) | [androidJvm]<br>constructor(repository: [MedicationRepository](../-medication-repository/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [allMedications](all-medications.md) | [androidJvm]<br>val [allMedications](all-medications.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Medication](../-medication/index.md)&gt;&gt; |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [deleteMedication](delete-medication.md) | [androidJvm]<br>fun [deleteMedication](delete-medication.md)(medicationId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [updateMedication](update-medication.md) | [androidJvm]<br>fun [updateMedication](update-medication.md)(medication: [Medication](../-medication/index.md)) |
| [updateMedicationTakenStatus](update-medication-taken-status.md) | [androidJvm]<br>fun [updateMedicationTakenStatus](update-medication-taken-status.md)(medicationId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), timestamp: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) |

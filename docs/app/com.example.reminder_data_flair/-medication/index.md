//[app](../../../index.md)/[com.example.reminder_data_flair](../index.md)/[Medication](index.md)

# Medication

[androidJvm]\
data class [Medication](index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val days: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val time: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val warningLabelDirections: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val emergencyContactNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val dosageCountPerDay: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val dosagePerNewBottle: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), var taken: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), var lastTakenDate: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = initializeDefaultLastTakenDate(), var currentDosageCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), var pastTimestamps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)&gt; = listOf())

## Constructors

| | |
|---|---|
| [Medication](-medication.md) | [androidJvm]<br>constructor(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), days: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), time: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), warningLabelDirections: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), emergencyContactNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), dosageCountPerDay: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), dosagePerNewBottle: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), taken: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), lastTakenDate: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = initializeDefaultLastTakenDate(), currentDosageCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), pastTimestamps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)&gt; = listOf()) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [currentDosageCount](current-dosage-count.md) | [androidJvm]<br>var [currentDosageCount](current-dosage-count.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [days](days.md) | [androidJvm]<br>val [days](days.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [dosageCountPerDay](dosage-count-per-day.md) | [androidJvm]<br>val [dosageCountPerDay](dosage-count-per-day.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [dosagePerNewBottle](dosage-per-new-bottle.md) | [androidJvm]<br>val [dosagePerNewBottle](dosage-per-new-bottle.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [emergencyContactNumber](emergency-contact-number.md) | [androidJvm]<br>val [emergencyContactNumber](emergency-contact-number.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [lastTakenDate](last-taken-date.md) | [androidJvm]<br>var [lastTakenDate](last-taken-date.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? |
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [pastTimestamps](past-timestamps.md) | [androidJvm]<br>var [pastTimestamps](past-timestamps.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)&gt; |
| [taken](taken.md) | [androidJvm]<br>var [taken](taken.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [time](time.md) | [androidJvm]<br>val [time](time.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [warningLabelDirections](warning-label-directions.md) | [androidJvm]<br>val [warningLabelDirections](warning-label-directions.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

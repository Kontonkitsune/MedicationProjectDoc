//[app](../../../index.md)/[com.example.reminder_data_flair](../index.md)/[Notification](index.md)

# Notification

[androidJvm]\
class [Notification](index.md) : [BroadcastReceiver](https://developer.android.com/reference/kotlin/android/content/BroadcastReceiver.html)

## Constructors

| | |
|---|---|
| [Notification](-notification.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [abortBroadcast](index.md#-1578158536%2FFunctions%2F-912451524) | [androidJvm]<br>fun [abortBroadcast](index.md#-1578158536%2FFunctions%2F-912451524)() |
| [clearAbortBroadcast](index.md#-547655405%2FFunctions%2F-912451524) | [androidJvm]<br>fun [clearAbortBroadcast](index.md#-547655405%2FFunctions%2F-912451524)() |
| [getAbortBroadcast](index.md#1852574954%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getAbortBroadcast](index.md#1852574954%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getDebugUnregister](index.md#-2066178064%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getDebugUnregister](index.md#-2066178064%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getResultCode](index.md#-1855658543%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getResultCode](index.md#-1855658543%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getResultData](index.md#485630644%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getResultData](index.md#485630644%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getResultExtras](index.md#1243983328%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getResultExtras](index.md#1243983328%2FFunctions%2F-912451524)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html) |
| [goAsync](index.md#478464125%2FFunctions%2F-912451524) | [androidJvm]<br>fun [goAsync](index.md#478464125%2FFunctions%2F-912451524)(): [BroadcastReceiver.PendingResult](https://developer.android.com/reference/kotlin/android/content/BroadcastReceiver.PendingResult.html) |
| [isInitialStickyBroadcast](index.md#-448034677%2FFunctions%2F-912451524) | [androidJvm]<br>fun [isInitialStickyBroadcast](index.md#-448034677%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isOrderedBroadcast](index.md#1250697259%2FFunctions%2F-912451524) | [androidJvm]<br>fun [isOrderedBroadcast](index.md#1250697259%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onReceive](on-receive.md) | [androidJvm]<br>open override fun [onReceive](on-receive.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?) |
| [peekService](index.md#-1162131393%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [peekService](index.md#-1162131393%2FFunctions%2F-912451524)(p0: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), p1: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)): [IBinder](https://developer.android.com/reference/kotlin/android/os/IBinder.html) |
| [setDebugUnregister](index.md#375803713%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setDebugUnregister](index.md#375803713%2FFunctions%2F-912451524)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [setOrderedHint](index.md#48379132%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setOrderedHint](index.md#48379132%2FFunctions%2F-912451524)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [setResult](index.md#455010187%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setResult](index.md#455010187%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p1: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), p2: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)) |
| [setResultCode](index.md#-1146739549%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setResultCode](index.md#-1146739549%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [setResultData](index.md#44586972%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setResultData](index.md#44586972%2FFunctions%2F-912451524)(p0: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [setResultExtras](index.md#1065610694%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setResultExtras](index.md#1065610694%2FFunctions%2F-912451524)(p0: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)) |

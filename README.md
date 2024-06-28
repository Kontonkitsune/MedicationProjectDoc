# Medication Reminder Application
This is an android application which is designed to remind users when they should take their medications, as well as tracking when users have taken their medications.

# How to use
NOTE: These features are PLANNED, and this description does not currently reflect what is described below.

Opening the app will bring you to the main dashboard, where you can see all the medications you're currently taking, as well as information on each of them. 
You can add a new medication from a button on the dashboard, which will take you to the add prescription page. 
Here, you can put information on your prescription, such as its name, the amount you have to take, and the schedule for when you need to take it.
Once you hit confirm, you will be taken back to the main page, and your new prescription should be displayed along with the rest.
You can click on a medication from the dashboard to look at its details, and through this sub-menu you can also edit the prescription or delete it.

You will be notified any time that you need to take a medication, as well as any time you to refill one of your prescriptions. 
When you receive a notification, you can specify that you have or haven't taken your medication. 

You can login to connect the application to an account, which will allow you to store your data on the cloud and sync it between devices.

# Planned Features
Dashboard:
- User dashboard, where they can see every medication they're taking.
- For each medication, the user can see the medication, times they should be taken, the descriptions on how it's taken, and other relevant details.
- A more detailed description of each medication can be accessed through the dashboard.

Prescription input:
- Through the dashboard, users can add a new medication to the list, which will take them to a form.
- Users can provide the name of the medications they are taking, prescription instructions, what times they need to be taken, and other optional categories.

Notifications:
- User will recieve notifications every time they need to take medications.
- Users will only receive one notification for all medications at a given time.
- The user can confirm that they've taken their medication through this notification, or they can snooze the notification.
- The user will receive notifications when their medications may be running low, tracked by the user when they confirm they've taken their medications.
- If the user has provided a method of contact for their prescription refill, this information will be provided when a user needs to refill their prescriptions.

Cloud storage:
- Users can connect their app to an account, so that they can store their data on the cloud and sync it between devices.

# Release Notes
June 27th, 2024: Currently, this is a work in progress, with most (All, at time of writing) of the code being from a tutorial found at https://projectgurukul.org/android-reminder-app/. 
The code in the master branch comes from the main branch, pruned to include only the necessary files for running the program on Android Studio.
Currently, this application only stores data per session, can only input one reminder at a time, and can only see the most recent reminder on a separate screen. There is also no way to get back to the second screen from the first.

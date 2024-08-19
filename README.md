# Medication Reminder Application
This is an android application which is designed to remind users when they should take their medications, as well as tracking when users have taken their medications.

# How to use
Opening the app will bring you to the main dashboard, where you can see all the medications you're currently taking, as well as information on each of them. 
You can add a new medication from a button on the dashboard, which will take you to the add prescription page. 
Here, you can put information on your prescription, such as its name, the amount you have to take, and the schedule for when you need to take it.
Once you hit confirm, you will be taken back to the main page, and your new prescription should be displayed along with the rest.
You can click on a medication from the dashboard to look at its details, and through this sub-menu you can also edit the prescription or delete it.

You will be notified any time that you need to take a medication. 
When you receive a notification, clicking on it will take you right to the dashboard, where you can input that you've taken your medication. 

# Implemented Features
Data:
- User data is stored on the local machine and persists between closing the app or restarting the device.

Dashboard:
- User dashboard, where they can see every medication they're taking.
- For each medication, the user can see the medication, times they should be taken, days of the week it should be taken.
- There'a a drop-down which allows you to see the remaining doses, and allows you to go to the prescription edit form.
- There are check-boxes on the right of each prescription which can be ticked to indicate that you've taken your medications at the given time.

Prescription input:
- Through the dashboard, users can add a new medication to the list, which will take them to a form.
- Users can provide the name of the medications they are taking, prescription instructions, what times they need to be taken, and other optional categories.
- Users can cancel the creation of adding a prescription
- Users can edit a prescription and update any of its properties to the database

Notifications:
- User will recieve notifications every time they need to take medications.
- Clicking a notification will take you to the dashboard

Medication History
- User can go to medication history from the dashboard.
- Medication history shows the exact date and times users have taken their meds.

# Release Notes
August 19th, 2024: This is the final version of the project, though we didn't manage to get everything we were originally planning to finished. Notifications have been successfully implemented, and to accompany this, a simple medication history has been added. Additionally, the amount of doses you have remaining will be properly tracked now. See features for more information.

July 25th, 2024: This is still a work in progress, though much of the code is now significantly modified. In this version, all of the functionality has been massively improved. The application can store your data between sessions, store multiple medications at the same time, and allow you to choose precisely what times and days you intend to take your medications. From the dashboard, you can add a medication, which will take you to a separate form where you can put all the details of your medication in, and then hit "accept". Additionally, from the dashboard, you can view more data of a medication, as well as editing it. Through this interface, you may also choose to delete a medication.

July 18th, 2024: While this is still a work in progress, we've made noticeable progress from the previous release. Now the dashboard is capable of displaying multiple medications, as well as displaying the name of the medication, and the time you wrote to take the medication. In addition, it will also show which days of the week you've selected to take the medications on the same display. The user can additionally add more medications to this form, though there is currently no way to remove or edit a prescription once it has been added. Additionally, the application can store the data between launches, so if you close the app, it will still retain your prescription data you've entered, and you can still see your medications displayed on the dashboard.

June 27th, 2024: Currently, this is a work in progress, with most (All, at time of writing) of the code being from a tutorial found at https://projectgurukul.org/android-reminder-app/. 
The code in the master branch comes from the main branch, pruned to include only the necessary files for running the program on Android Studio.
Currently, this application only stores data per session, can only input one reminder at a time, and can only see the most recent reminder on a separate screen. There is also no way to get back to the second screen from the first.


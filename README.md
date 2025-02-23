Setup and Run Instructions

Prerequisites

Android Studio (latest version recommended)

Gradle (bundled with Android Studio)

Firebase account with a configured project

An active internet connection

Steps to Run

Clone the repository from the given link:

git clone https://github.com/sarthak1103/TaskManagement/tree/master
cd task-manager-app

Open the project in Android Studio.

Sync the Gradle files and build the project.

Run the app on an emulator or physical device.

Third-Party Library Integration

The app integrates the following third-party libraries:

Firebase Analytics: Tracks key events like "Task Added," "Task Edited," and "Task Completed".

Firebase Performance Monitoring: Monitors network performance.

Firebase Crashlytics: Captures crashes, including a forced database crash.

Retrofit: Used for API communication.

Room Database: Stores task data locally.

Jetpack Compose: UI implementation.

Hilt: Dependency injection.

JUnit: Used for unit testing.

MockK: Mocking framework for Kotlin testing.

Material Icons: Provides UI icons following Material Design principles.

Design Decisions

MVVM Architecture: Ensures separation of concerns and testability.

LazyColumn: Provides a modern UI approach while ensuring smooth scrolling for task management.

Room Database: Used for local data persistence to allow offline access.

Firebase Analytics & Crashlytics: Ensures real-time monitoring and debugging.

Firebase Analytics Events

Screenshots of Firebase Analytics events such as:

"Task Added"

"Task Edited"

"Task Completed"

<img width="1470" alt="image" src="https://github.com/user-attachments/assets/5e0f20ca-cdb8-4fef-816c-fdd4f12a3c35" />  

Crash Report & Network Performance

Crash Reports

Forced app crash due to a simulated database error in Room.

Firebase Crashlytics captures these crashes.

<img width="1468" alt="image" src="https://github.com/user-attachments/assets/c05705a8-1e8e-4853-a435-3cecb1509247" />  - crashlytics 

Network Performance

Captured API request/response logs.

Firebase Performance Monitoring tracks response times.

<img width="1469" alt="image" src="https://github.com/user-attachments/assets/35a7c4b0-b7e4-452e-af29-cf2880384f4f" /> - network performance 


Screenshots
![Screenshot_20250223_165536](https://github.com/user-attachments/assets/1bb463b0-fac2-48b6-a93a-322386ad0855)  - Home Screen UI 


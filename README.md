This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…


**Bike management**


Application helps you manage your bikes with their respective service intervals and rides.

You can keep track of your rides adding details about duration, length of the ride, bike and date.

You can personalise your bike by choosing its type, color, wheel size and service interval.

For your defoult bike the app will send you a notification to inform the service interval is approaching.


Application also offers support for dark and light theme for iOS and adaptive theme colors for Android.


**Technology stack:**


Clean arhitecture with MVVM.

Presentation layer: Jetpack Compose, View Model, Compose Navigation, Material3 and custom UI Components

Domain layer: HILT for dependency injection, Work Manager for background work, Coroutines for asynchronous tasks

Data layer: Room database, Kotlin Flow





![Screenshot 2024-05-14 at 11 31 03](https://github.com/vladbala17/bike-app/assets/9413167/133808d4-9ea9-4c51-8fdb-de577866e719)

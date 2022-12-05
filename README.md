[![GitHub license](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

# 🗒️ Notes

A minimal Notes app built using Kotlin Multiplatform Mobile. The UI is built natively using Jetpack Compose for Android and SwiftUI for iOS. This app is a demonstration of using local database with SQLDelight and Parcelize support for Android in KMM.

Read more about Parcelize support for Android in KMM: 

https://medium.com/@amsavarthan/using-parcelable-and-custom-typeparceler-in-kotlin-multiplatform-mobile-kmm-c99203bd2e

***Try latest app apk from below 👇***

[![Notes Android](https://img.shields.io/badge/Notes_Android-APK-black.svg?style=for-the-badge&logo=android)](https://github.com/amsavarthan/NotesKmm/releases/download/v1.0.0/notes-android.apk)

## 🟢 Android Screenshots

|   All Notes    | Note Details    |   Search Notes
|---	|---	|---
|  ![](https://github.com/amsavarthan/NotesKmm/blob/main/art/android/note_list.png)    |  ![](https://github.com/amsavarthan/NotesKmm/blob/main/art/android/note_detail.png)    |   ![](https://github.com/amsavarthan/NotesKmm/blob/main/art/android/note_search.png)    


## 🍎 iOS Screenshots

|   All Notes    | Note Details    |   Search Notes
|---	|---	|---
|  ![](https://github.com/amsavarthan/NotesKmm/blob/main/art/ios/note_list.png)    |  ![](https://github.com/amsavarthan/NotesKmm/blob/main/art/ios/note_detail.png)    |   ![](https://github.com/amsavarthan/NotesKmm/blob/main/art/ios/note_search.png)    

## 🌚 Dark Mode

|   Dark Mode & Dynamic Color Support (Android)    | Dark Appearance (iOS) 
|---	|---
|  ![](https://github.com/amsavarthan/NotesKmm/blob/main/art/android/note_dark.png)    |  ![](https://github.com/amsavarthan/NotesKmm/blob/main/art/ios/note_dark.png)


<br/>

## 🛠 Built With

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android
  development.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s
  modern toolkit for building native UI.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - A coroutine is a
  concurrency design pattern that you can use on Android to simplify code that executes
  asynchronously.
- [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous
  version of a Sequence, a type of collection whose values are lazily produced.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) -
  Collection of libraries that help you design robust, testable, and maintainable apps.
    - [Stateflow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - StateFlow is
      a state-holder observable flow that emits the current and new state updates to its collectors.
    - [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous
      version of a Sequence, a type of collection whose values are lazily produced.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
      UI-related data that isn"t destroyed on UI changes.
    - [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation) - The
      Navigation component provides support for Jetpack Compose applications.
- [SwiftUI](https://developer.apple.com/xcode/swiftui/) - SwiftUI helps you build great-looking apps across all Apple platforms with the power of Swift

<br />

## 📚 KMM Libraries

- [DateTime](https://github.com/Kotlin/kotlinx-datetime) - KotlinX multiplatform date/time library
- [Serialization](https://github.com/Kotlin/kotlinx.serialization) - Kotlin multiplatform / multi-format serialization
- [SQLDelight](https://github.com/cashapp/sqldelight) - SQLDelight - Generates typesafe Kotlin APIs from SQL

<br>

## 🧰 Build-tool

- [Android Studio Dolphin 2021.3.1 or above](https://developer.android.com/studio)
- [Xcode 14 or above](https://developer.apple.com/xcode/)

<br>

## 📩 Contact

DM me at 👇

* Twitter: <a href="https://twitter.com/lvamsavarthan" target="_blank">@lvamsavarthan</a>
* Email: amsavarthan.a@gmail.com

<br>

## 🔖 License

```
MIT License

Copyright (c) 2022 Amsavarthan Lv

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

This is a Kotlin Multiplatform project targeting Android, iOS.

* [/androidApp](./androidApp/src/main/kotlin) contains an Android application. This is an entry
  point to Android application. This is where you should add Compose code for your project.

* [/iosApp](./iosApp/iosApp) contains an iOS application. This is an entry point to iOS application.
  This is also where you should add SwiftUI code for your project.

* [/shared](./shared/src) is for the code that will be shared between app targets in the project.
  The most important subfolder is [commonMain](./shared/src/commonMain/kotlin). If preferred, you
  can add code to the platform-specific folders here too.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these
commands and options:

- Android app: `./gradlew :androidApp:assembleDebug`
- iOS app: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Android tests: `./gradlew :shared:testAndroidHostTest`
- iOS tests: `./gradlew :shared:iosSimulatorArm64Test`

---

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

# vegidio/android-parsesdk-reactive

[![](https://jitpack.io/v/vegidio/android-parsesdk-reactive.svg)](https://jitpack.io/#vegidio/android-parsesdk-reactive)
[![Apache 2.0](https://img.shields.io/badge/license-Apache_License_2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

Set of Android libraries that let you use the [SDK](https://github.com/parse-community/Parse-SDK-Android) for Parse Server using reactive streams through **RxJava** and **Kotlin Coroutines**.

## 🧩 Installation

Add this in the root `build.gradle` file of your project (**not** your module's `build.gradle` file):

```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Then, add the library that you need in your module's `build.gradle`:

```groovy
dependencies {
    // For Kotlin Coroutines support
    implementation "com.github.vegidio:android-parsesdk-reactive:coroutines:1.0.0"

    // For RxJava 3 support
    implementation "com.github.vegidio:android-parsesdk-reactive:rxjava:1.0.0"
}
```

## 🤖 Usage

Refer to the specific documentation file, depending on if you are planning to use [RxJava](rxjava) or [Kotlin Coroutines](coroutines) with your Parse SDK.

## 📝 License

**vegidio/android-parsesdk-reactive** is released under the Apache License. See [LICENSE](LICENSE.txt) for details.

## 👨🏾‍💻 Author

Vinicius Egidio ([vinicius.io](http://vinicius.io))
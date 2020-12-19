/*
* Dependencies and version variables are kept here.
*/

object Versions {

    // Gradle
    const val buildToolsVersion = "30.0.2"
    const val compileSdkVersion = 30
    const val minSdkVersion = 23
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0"
    const val androidGradlePlugin = "4.0.1"

    // Kotlin
    const val kotlin = "1.4.10"

    // Support
    const val supportCore = "1.3.2"
    const val supportAppcompat = "1.2.0"
    const val supportMaterial = "1.2.1"
    const val supportConstraintLayout = "1.1.3"

    // Test
    const val junit = "4.13"
    const val hamcrest = "1.3"
    const val mockito = "3.4.6"
    const val mockitoKotlin = "2.2.0"
    const val junitExt = "1.1.1"

    // Other
    const val koin = "2.2.0"
    const val koinArchitecture = "0.8.2"
    const val retrofit = "2.9.0"
    const val loggingInterceptor = "3.8.0"
    const val gson = "2.8.6"
    const val rxAndroid2 = "2.1.1"
    const val rxJava2 = "2.2.19"
    const val picasso = 2.71828
}

object Dependencies {

    // Gradle
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"

    // Kotlin
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    // Support
    const val supportCoreKtx = "androidx.core:core-ktx:${Versions.supportCore}"
    const val supportAppCompat = "androidx.appcompat:appcompat:${Versions.supportAppcompat}"
    const val supportMaterial = "com.google.android.material:material:${Versions.supportMaterial}"
    const val supportConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.supportConstraintLayout}"

    // Koin
    const val koinGradle = "org.koin:koin-gradle-plugin:${Versions.koin}"
    const val koinExtensions = "org.koin:koin-android-scope:${Versions.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinFragment = "org.koin:koin-androidx-fragment:${Versions.koin}"
    const val koinArchitecture = "org.koin:koin-android-architecture:${Versions.koinArchitecture}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    // Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    // RxJava
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid2}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2}"

    // Picasso
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    // Test
    const val junit = "junit:junit:${Versions.junit}"
    const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"
    const val mockito = "org.mockito:mockito-inline:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
}

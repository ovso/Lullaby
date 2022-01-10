@file:Suppress("SpellCheckingInspection")

import org.gradle.kotlin.dsl.DependencyHandlerScope

object Dependencies {

  const val APPLICATION_ID = "io.github.ovso.lullaby"
  const val COMPILE_SDK = 31
  const val MIN_SDK = 26
  const val TARGET_SDK = 31
  const val VERSION_CODE = 1
  const val VERSION_NAME = "1.0"

  private const val implementation = "implementation"
  private const val testImplementation = "testImplementation"
  private const val androidTestImplementation = "androidTestImplementation"
  private const val debugImplementation = "debugImplementation"

  private const val kapt = "kapt"
  private const val kaptTest = "kaptTest"
  private const val kaptAndroidTest = "kaptAndroidTest"


  object Kotlin {
    private const val VERSION = "1.6.0"
    const val STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:$VERSION"
    const val TEST_JUNIT = "org.jetbrains.kotlin:kotlin-test-junit:$VERSION"

    const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
    const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0"
    const val COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"

    const val SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
  }

  fun DependencyHandlerScope.implementationKotlin() {
    implementation(Kotlin.STD_LIB)
    implementation(Kotlin.TEST_JUNIT)
    implementation(Kotlin.COROUTINE_ANDROID)
    implementation(Kotlin.COROUTINE_CORE)
    implementation(Kotlin.COROUTINE_TEST)
    implementation(Kotlin.SERIALIZATION)
  }

  object Google {
    const val MATERIAL = "com.google.android.material:material:1.4.0"
  }

  fun DependencyHandlerScope.implementationGoogle() {
    implementation(Google.MATERIAL)
  }

  object AndroidX {
    const val CORE = "androidx.core:core-ktx:1.7.0"
    const val APPCOMPAT = "androidx.appcompat:appcompat:1.4.0"
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:1.4.0"
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:2.4.0-rc01"
    const val WINDOW = "androidx.window:window:1.0.0-rc01"
    const val TEST_EXT_JUNIT = "androidx.test.ext:junit:1.1.3"
    const val TEST_ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
  }

  fun DependencyHandlerScope.implementationAndroidX() {
    implementation(AndroidX.APPCOMPAT)
    implementation(AndroidX.CORE)
    implementation(AndroidX.ACTIVITY_COMPOSE)
    implementation(AndroidX.NAVIGATION_COMPOSE)
    implementation(AndroidX.WINDOW)
    androidTestImplementation(AndroidX.TEST_EXT_JUNIT)
    androidTestImplementation(AndroidX.TEST_ESPRESSO_CORE)
  }

  object Retrofit2 {
    private const val VERSION = "2.9.0"
    const val CORE = "com.squareup.retrofit2:retrofit:${VERSION}"
    const val MOSHI = "com.squareup.retrofit2:converter-moshi:${VERSION}"
    const val SCALARS = "com.squareup.retrofit2:converter-scalars:${VERSION}"
  }

  fun DependencyHandlerScope.implementationRetrofit2() {
    implementation(Retrofit2.CORE)
    implementation(Retrofit2.MOSHI)
    implementation(Retrofit2.SCALARS)
  }

  object Compose {
    private const val VERSION = "1.0.5"

    const val UI = "androidx.compose.ui:ui:$VERSION"
    const val PREVIEW = "androidx.compose.ui:ui-tooling-preview:$VERSION"
    const val MATERIAL = "androidx.compose.material:material:$VERSION"
    const val MATERIAL_ICONS = "androidx.compose.material:material-icons-extended:$VERSION"
    const val UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4:$VERSION"
    const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$VERSION"
  }

  fun DependencyHandlerScope.implementationCompose() {
    implementation(Compose.UI)
    implementation(Compose.PREVIEW)
    implementation(Compose.MATERIAL)
    implementation(Compose.MATERIAL_ICONS)
    androidTestImplementation(Compose.UI_TEST_JUNIT4)
    debugImplementation(Compose.UI_TOOLING)
  }

  object Lifecycle {
    private const val VERSION = "2.4.0"
    const val VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:$VERSION"
    const val VIEWMODEL_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:$VERSION"
    const val VIEWMODEL_SAVEDSTATE = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$VERSION"
    const val RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:$VERSION"
    const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:$VERSION"
  }

  fun DependencyHandlerScope.implementationLifecycle() {
    implementation(Lifecycle.LIVEDATA)
    implementation(Lifecycle.RUNTIME)
    implementation(Lifecycle.VIEWMODEL_COMPOSE)
    implementation(Lifecycle.VIEWMODEL_KTX)
    implementation(Lifecycle.VIEWMODEL_SAVEDSTATE)
  }

  object Accompanist {
    private const val VERSION = "0.22.0-rc"
    const val SWIPEREFRESH = "com.google.accompanist:accompanist-swiperefresh:$VERSION"
    const val INSETS = "com.google.accompanist:accompanist-insets:$VERSION"
    const val SYSTEMUICONTROLLER = "com.google.accompanist:accompanist-systemuicontroller:$VERSION"
  }

  fun DependencyHandlerScope.implementationAccompanist() {
    implementation(Accompanist.SWIPEREFRESH)
    implementation(Accompanist.INSETS)
    implementation(Accompanist.SYSTEMUICONTROLLER)
  }


  object Glide {
    private const val VERSION = "4.12.0"
    const val CORE = "com.github.bumptech.glide:glide:$VERSION"
    const val COMPILER = "com.github.bumptech.glide:compiler:$VERSION"
    const val OKHTTP3_INTEGRATION = "com.github.bumptech.glide:okhttp3-integration:$VERSION"
    const val TRANSFORMATIONS = "jp.wasabeef:glide-transformations:4.3.0"
  }

  fun DependencyHandlerScope.implementationGlide() {
    implementation(Glide.CORE)
    kapt(Glide.COMPILER)
    implementation(Glide.OKHTTP3_INTEGRATION)
  }

//    object JakeWharton{
//        const val THREE_TEN_ABP = "com.jakewharton.threetenabp:threetenabp:${Versions.threetenabp}"
//        const val TIMBER = "com.jakewharton.timber:timber:${Versions.timber}"
//        const val RXRELAY = "com.jakewharton.rxrelay2:rxrelay:${Versions.rxrelay2}"
//        const val RXBINDING = "com.jakewharton.rxbinding2:rxbinding:${Versions.rxbinding}"
//    }

  object Test {
    const val JUNIT = "junit:junit:4.13.2"
    const val ANDROID_EXT_JUNIT = "androidx.test.ext:junit:1.1.3"
    const val ANDROID_ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
  }

  fun DependencyHandlerScope.implementationTest() {
    Dependencies.testImplementation(Test.JUNIT)
  }

  object Hilt {
    private const val VERSION = "2.40.5"

    const val CORE = "com.google.dagger:hilt-android:$VERSION"
    const val COMPILER = "com.google.dagger:hilt-compiler:$VERSION"

    // For instrumentation tests
    const val ANDROID_TESTING = "com.google.dagger:hilt-android-testing:$VERSION"
    const val ANDROID_TESTING_COMPILER = "com.google.dagger:hilt-compiler:$VERSION"

    // For local unit tests
    const val LOCAL_TESTING = "com.google.dagger:hilt-android-testing:$VERSION"
    const val LOCAL_TESTING_COMPILER = "com.google.dagger:hilt-compiler:$VERSION"
  }

  fun DependencyHandlerScope.implementationHilt() {
    implementation(Hilt.CORE)
    kapt(Hilt.COMPILER)

    kaptTest(Hilt.LOCAL_TESTING_COMPILER)
    testImplementation(Hilt.LOCAL_TESTING)

    androidTestImplementation(Hilt.ANDROID_TESTING)
    kaptAndroidTest(Hilt.ANDROID_TESTING_COMPILER)
  }


}

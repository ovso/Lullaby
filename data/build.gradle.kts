import Dependencies.implementationHilt
import Dependencies.implementationKotlin
import Dependencies.implementationKotlinX
import Dependencies.implementationRetrofit2
import Dependencies.implementationTest

plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
  id("kotlinx-serialization")
}

android {
  compileSdk = Dependencies.COMPILE_SDK

  defaultConfig {
    minSdk = Dependencies.MIN_SDK
    targetSdk = Dependencies.TARGET_SDK
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation(project(":domain"))
  implementation(Dependencies.AndroidX.CORE)
  implementation(Dependencies.AndroidX.APPCOMPAT)
  implementation(Dependencies.Google.MATERIAL)
  implementationKotlin()
  implementationKotlinX()
  implementationHilt()
  implementationRetrofit2()
  implementationTest()
}

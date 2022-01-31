import Dependencies.implementationAccompanist
import Dependencies.implementationAds
import Dependencies.implementationAndroidX
import Dependencies.implementationCompose
import Dependencies.implementationGoogle
import Dependencies.implementationHilt
import Dependencies.implementationKotlin
import Dependencies.implementationLifecycle
import Dependencies.implementationTest

plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-kapt")
  id("kotlinx-serialization")
  id("kotlin-parcelize")
}

android {
  compileSdk = Dependencies.COMPILE_SDK

  defaultConfig {
    applicationId = Dependencies.APPLICATION_ID
    minSdk = Dependencies.MIN_SDK
    targetSdk = Dependencies.TARGET_SDK
    versionCode = Dependencies.VERSION_CODE
    versionName = Dependencies.VERSION_NAME

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      isShrinkResources = true
      isDebuggable = false
      proguardFile(getDefaultProguardFile("proguard-android.txt"))
      // global proguard settings
      proguardFile(file("proguard-rules.pro"))
      // library proguard settings
      val files = rootProject.file("proguard")
        .listFiles()
        ?.filter { it.name.startsWith("proguard") }
        ?.toTypedArray() ?: arrayOf()

      proguardFiles(*files)
    }

    debug {
      isDebuggable = true
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
    viewBinding = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.0.5"
  }
  packagingOptions {
    resources {
      with(excludes) {
        add("/META-INF/{AL2.0,LGPL2.1}")
      }
    }
  }
}

dependencies {
  implementationAndroidX()
  implementationGoogle()
  implementationCompose()
  implementationLifecycle()
  implementationAccompanist()
  implementationKotlin()
  implementationHilt()
  implementationAds()
  implementationTest()
}

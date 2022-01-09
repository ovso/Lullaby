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
            isMinifyEnabled = false
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

    val compose_version = "1.0.5"
    val accompanist_version = "0.21.3-beta"

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.material:material-icons-extended:$compose_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")

    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanist_version")
    implementation("com.google.accompanist:accompanist-insets:$accompanist_version")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanist_version")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.4.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")

    implementation("androidx.navigation:navigation-compose:2.4.0-rc01")

    implementation("androidx.window:window:1.0.0-rc01")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
}
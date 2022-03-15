// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:7.0.4")
    classpath(Dependencies.Kotlin.GRADLE_PLUGIN)
    classpath(Dependencies.Kotlin.SERIALIZATION)
    classpath(Dependencies.Hilt.ANDROID_GRADLE_PLUGIN)
    classpath(Dependencies.Google.GOOGLE_SERVICES)

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
  }
}

plugins {
  id("com.diffplug.gradle.spotless") version "3.27.1"
}

subprojects {
  apply(plugin = "com.diffplug.gradle.spotless")
  val ktlintVer = "0.43.2"
  spotless {
    kotlin {
      target("**/*.kt")
      ktlint(ktlintVer).userData(
        mapOf("max_line_length" to "100", "disabled_rules" to "import-ordering")
      )
      licenseHeaderFile(project.rootProject.file("copyright.kt"))
      trimTrailingWhitespace()
      indentWithSpaces()
      endWithNewline()
    }
    kotlinGradle {
      // same as kotlin, but for .gradle.kts files (defaults to '*.gradle.kts')
      target("**/*.gradle.kts")
      ktlint(ktlintVer)
      licenseHeaderFile(
        project.rootProject.file("copyright.kt"),
        "(plugins |import |include)"
      )
      trimTrailingWhitespace()
      indentWithSpaces()
      endWithNewline()
    }
    format("misc") {
      target("**/*.md", "**/.gitignore")
      trimTrailingWhitespace()
      indentWithSpaces()
      endWithNewline()
    }
  }
}

plugins {
  id("java-library")
  id("kotlin")
  id("kotlinx-serialization")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
  implementation(Dependencies.KotlinX.COROUTINE_CORE)
  implementation(Dependencies.KotlinX.SERIALIZATION_JSON)
}

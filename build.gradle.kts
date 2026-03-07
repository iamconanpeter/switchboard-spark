plugins {
    id("com.android.application") version "7.0.0" apply false
    kotlin("jvm") version "1.9.0" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

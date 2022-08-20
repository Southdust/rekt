@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(rekt.plugins.kotlin.multiplatform) apply false
    alias(rekt.plugins.kotlin.serialization) apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}
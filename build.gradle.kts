@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(rekt.plugins.kotlin.multiplatform) apply false
    alias(rekt.plugins.kotest.multiplatform) apply false
    alias(rekt.plugins.kotlin.serialization) apply false
}

allprojects {
    repositories {
        maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/") {
            name = "Sonatype"
        }
        mavenCentral()
    }
}
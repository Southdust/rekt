plugins {
    id("mpp-conventions")
    id("publishing-conventions")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(rekt.data.common)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(rekt.lettuce.core)
            }
        }
    }
}

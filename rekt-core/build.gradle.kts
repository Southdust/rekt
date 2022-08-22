plugins {
    id("mpp-conventions")
    id("publishing-conventions")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(rekt.data.common)
                implementation(rekt.kotlin.logging)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(rekt.lettuce.core)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(npm("redis", rekt.versions.redis.js.get()))
            }
        }
    }
}

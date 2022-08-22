package org.hexalite.rekt.core

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec
import kotlinx.serialization.builtins.serializer
import org.hexalite.rekt.core.extension.find
import org.hexalite.rekt.core.extension.modify
import org.hexalite.rekt.core.util.env
import org.hexalite.rekt.core.util.leftShouldBe
import org.hexalite.rekt.core.util.shouldBeLeft

class RedisClientTest : StringSpec({
    val enabled = env("REDIS_CLIENT_TEST_ENABLED") == "true"
    val redis = redis {
        connection {
            address {
                hostname = env("REDIS_HOST") ?: "127.0.0.1"
                port = env("REDIS_PORT")?.toShort() ?: 6379
                password = env("REDIS_PASSWORD") ?: "password"
                username = env("REDIS_USERNAME")
            }
        }
    }

    "should connect to redis without any issues".config(enabled) {
        redis.connect().shouldBeLeft()
    }
    "should set a value successfully".config(enabled) {
        shouldNotThrowAny {
            redis.commands().left().apply {
                modify("hello", "world", String.serializer()).shouldBeLeft()
                find("hello", String.serializer()) leftShouldBe "world"
            }
        }
    }
    "should set a value successfully using extensions".config(enabled) {
        shouldNotThrowAny {
            redis.commands().left().apply {
                modify("hello2", "world2").shouldBeLeft()
                find<String>("hello2") leftShouldBe "world2"
            }
        }
    }
})
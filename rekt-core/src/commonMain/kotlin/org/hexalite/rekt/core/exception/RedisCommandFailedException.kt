package org.hexalite.rekt.core.exception

import kotlin.reflect.KClass

sealed class RedisCommandFailedException(override val message: String, override val cause: Throwable? = null) :
    RuntimeException(message, cause) {
    data class TimedOut(val command: KClass<*>) :
        RedisCommandFailedException("Failed to dispatch '${command.simpleName}'; timed out")

    data class Custom(override val message: String, override val cause: Throwable?) :
        RedisCommandFailedException(message, cause)
}

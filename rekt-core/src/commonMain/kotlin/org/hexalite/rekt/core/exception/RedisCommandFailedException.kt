package org.hexalite.rekt.core.exception

import kotlin.reflect.KClass

public sealed class RedisCommandFailedException(override val message: String, override val cause: Throwable? = null) :
    RuntimeException(message, cause) {
    public data class TimedOut(val command: KClass<*>) :
        RedisCommandFailedException("Failed to dispatch '${command.simpleName}'; timed out")

    public data class Exception(override val cause: Throwable? = null) :
        RedisCommandFailedException("An exception was throwing during the dispatch execution", cause)

    public data class Default(val `class`: KClass<*>, val name: String) :
        RedisCommandFailedException("Failed to execute '${`class`.simpleName}' [$name].")

    public companion object {
        public val Disconnected: Exception = Exception(CommandScopeNotAccessibleException.Disconnected)
    }
}

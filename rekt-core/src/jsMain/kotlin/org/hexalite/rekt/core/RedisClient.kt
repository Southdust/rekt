package org.hexalite.rekt.core

import kotlinx.coroutines.flow.Flow
import mu.KLogger
import mu.KotlinLogging
import org.hexalite.stronghold.data.functional.Either
import org.hexalite.rekt.core.command.RedisCommandsScope
import org.hexalite.rekt.core.configuration.RedisConfiguration
import org.hexalite.rekt.core.exception.CommandScopeNotAccessibleException
import org.hexalite.rekt.core.exception.ConnectionFailedException
import org.hexalite.rekt.core.exception.DisconnectionFailedException

/**
 * Creates a new [RedisClient] instance from the given [configuration].
 */
public actual fun RedisClient(configuration: RedisConfiguration): RedisClient {
    TODO("Not yet implemented")
}

/**
 * A multiplatform coroutine-based wrapper for popular Redis clients based on JavaScript and JVM technologies:
 * [`node-redis`](https://github.com/redis/node-redis), [`lettuce-core`](https://github.com/lettuce-io/lettuce-core).
 *
 * ### Fundaments of this library
 *
 * The fundamental concept of this library is being able to access Redis from most common Kotlin platforms in a
 * convenient way and being able to gain readability and flexibility through coroutines and serialization support. This
 * of course includes using reactive-like and functional-inspired functions, such as [Either] and [Flow].
 *
 * ### Getting Started
 *
 * First of all, you need to create an instance of this client first using `RedisClient(configuration)`. This can be
 * made easier by using a builder DSL through `redis { ... }`.
 *
 * To have access to the [commands scope][commands], where you use all essential Redis commands through a Kotlin
 * interface, you need to create a connection first through [connect]. Also, if the required functionality is not
 * available on our interfaces, you could use [RedisCommandsScope.raw] to run any commands that aren't exposed by yourself.
 *
 * @author FromSyntax
 * @author Gabriel
 */
public actual class RedisClient(public val configuration: RedisConfiguration, unit: Unit) {
    internal actual val logger: KLogger? = KotlinLogging.logger {}
        get() {
            if (!configuration.enableLogging) {
                return null
            }
            return field
        }

    /**
     * All stuff that do not require an instance of [RedisClient] directly.
     */
    public actual companion object {
    }

    /**
     * Returns the configuration for this [RedisClient].
     */
    public actual fun configuration(): RedisConfiguration {
        TODO("Not yet implemented")
    }

    /**
     * Creates a connection to Redis and alter the state of this client to connected, therefore allowing the
     * [commands scope][commands] to be accessible. Any errors are be bound to the right side of the returned
     * [Either] type.
     */
    public actual suspend fun connect(): Either<RedisClient, ConnectionFailedException> {
        TODO("Not yet implemented")
        return Either.left(this)
    }

    /**
     * Closes the active Redis connection and alter the state of client to disconnected. Is worth nothing that
     * it will make the [commands scope][commands] be unavailable. Any errors are be bound to the right side of
     * the returned [Either] type.
     */
    public actual suspend fun disconnect(): Either<RedisClient, DisconnectionFailedException> {
        TODO("Not yet implemented")
        return Either.left(this)
    }

    /**
     * Creates a new commands scope from the active connection, or bound an error to the right of the returned
     * [Either] type if not available.
     */
    public actual fun commands(): Either<RedisCommandsScope, CommandScopeNotAccessibleException> {
        TODO("Not yet implemented")
    }

    /**
     * Returns whether this [RedisClient] is at an active (connected) state.
     */
    public actual fun isActive(): Boolean {
        TODO("Not yet implemented")
    }
}
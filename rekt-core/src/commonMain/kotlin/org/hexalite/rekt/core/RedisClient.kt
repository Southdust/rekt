package org.hexalite.rekt.core

import kotlinx.coroutines.flow.Flow
import mu.KLogger
import org.hexalite.stronghold.data.functional.Either
import org.hexalite.rekt.core.annotation.RedisDsl
import org.hexalite.rekt.core.command.RedisCommandsScope
import org.hexalite.rekt.core.configuration.RedisConfiguration
import org.hexalite.rekt.core.exception.CommandScopeNotAccessibleException
import org.hexalite.rekt.core.exception.ConnectionFailedException
import org.hexalite.rekt.core.exception.DisconnectionFailedException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Creates a new [RedisClient] instance from the given [configuration].
 */
public expect fun RedisClient(configuration: RedisConfiguration): RedisClient

/**
 * Creates a new [RedisClient] instance from the configuration built from the given [builder]. This works
 * by creating an empty configuration then applying the [builder] DSL to it then passing it as argument to
 * the [RedisClient] constructor.
 */
@RedisDsl
@OptIn(ExperimentalContracts::class)
public inline fun redis(builder: RedisConfiguration.() -> Unit): RedisClient {
    contract {
        callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
    }
    return RedisClient(RedisConfiguration().apply(builder))
}

/**
 * A multiplatform coroutine-based wrapper for popular Redis clients based on JavaScript and JVM technologies:
 * [`node-redis`](https://github.com/redis/node-redis), [`lettuce-core`](https://github.com/lettuce-io/lettuce-core).
 *
 * ### Fundamentals of this library
 *
 * The fundamental concepts of this library are being able to access Redis from most common Kotlin platforms in a
 * convenient way while still being able to have readability and flexibility through coroutines and serialization
 * support. This of course includes using reactive-like and functional-inspired types, such as [Either] and [Flow].
 * Of course, we couldn't forget about the big plus that is having total understanding of how this library works
 * through the power of documentation.
 *
 * ### Limitations
 *
 * There are a few things that can't make we extract all of our spare time to focus on software like this and instead
 * may limit the experience of development for both us, maintainers of this library, and you users of this library:
 * * [kotest/kotest#3059](https://github.com/kotest/kotest/issues/3059) - This limits testing for us in Kotlin for
 *   JavaScript platforms.
 * * Context receivers do not work with Kotlin for JavaScript platforms in the latest Kotlin/Multiplatform
 *   1.7.20-Beta.
 * * The limited amount of contributors. At the time this documentation is being written, there are only 2
 *   developers dedicating their spare time to maintain this library.
 * * **At the moment, only a few features are implemented.** Since this library is relatively new, for now there are
 *   only a few commands implemented. Of course, we are planning to increase the amount of commands, but this doesn't
 *   mean you can't implement them by your own or simply using [RedisCommandsScope.raw]!
 * * The library depends on `stronghold-data-common`, even though it is completely unrelated to this library. We are
 *   already working in a solution for this problem! We are developing a multiplatform common utility module only
 *   containing required stuff for libraries like this working fine while having nifty functionalities.
 *
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
public expect class RedisClient {
    internal val logger: KLogger?

    /**
     * Returns the configuration for this [RedisClient].
     */
    public fun configuration(): RedisConfiguration

    /**
     * Creates a connection to Redis and alter the state of this client to connected, therefore allowing the
     * [commands scope][commands] to be accessible. Any errors are be bound to the right side of the returned
     * [Either] type.
     */
    public suspend fun connect(): Either<RedisClient, ConnectionFailedException>

    /**
     * Closes the active Redis connection and alter the state of client to disconnected. Is worth nothing that
     * it will make the [commands scope][commands] be unavailable. Any errors are be bound to the right side of
     * the returned [Either] type.
     */
    public suspend fun disconnect(): Either<RedisClient, DisconnectionFailedException>

    /**
     * Creates a new commands scope from the active connection, or bound an error to the right side of the
     * returned [Either] type if not available.
     */
    public fun commands(): Either<RedisCommandsScope, CommandScopeNotAccessibleException>

    /**
     * Returns whether this [RedisClient] is at an active (connected) state. If possible, it will check the
     * activeness state of the underlying/delegated (platform-specific) client, or simply check if the delegate is
     * not null.
     */
    public fun isActive(): Boolean

    /**
     * All stuff that do not require an instance of [RedisClient] directly.
     */
    public companion object {
    }
}

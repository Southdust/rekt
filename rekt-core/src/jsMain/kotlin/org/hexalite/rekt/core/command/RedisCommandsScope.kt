package org.hexalite.rekt.core.command

import org.hexalite.stronghold.data.functional.Either
import org.hexalite.rekt.core.RedisClient
import org.hexalite.rekt.core.exception.RedisCommandFailedException

/**
 * A convenient wrapper for the most common Redis commands with direct support for kotlinx.serialization. This can
 * be easily accessed by a [RedisClient] at an active state.
 */
actual class RedisCommandsScope {
    actual fun <R> dispatch(command: AbstractRedisCommand<*, R>): Either<R, RedisCommandFailedException> {
        TODO("Not yet implemented")
    }
}
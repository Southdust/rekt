package org.hexalite.rekt.core.command

import org.hexalite.stronghold.data.functional.Either
import org.hexalite.rekt.core.exception.RedisCommandFailedException

/**
 * A generic type for a Redis command.
 *
 * @param T
 * The context required to execute this command.
 *
 * @param R
 * The type that this command would return if successful.
 *
 * @author FromSyntax
 */
abstract class AbstractRedisCommand<T: AbstractRedisContext, R> {
    /**
     * Executes this command at the given [context], if the connection is currently active.
     * @param context
     */
    abstract suspend fun execute(context: T): Either<R, RedisCommandFailedException>
}
package org.hexalite.rekt.core.command

import io.lettuce.core.ExperimentalLettuceCoroutinesApi
import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either
import org.hexalite.stronghold.data.functional.Either.Companion.either

/**
 * A command to authenticate to the Redis server using a [password][AuthenticationContext.password], and an
 * [username][AuthenticationContext.username] if provided.
 * @author FromSyntax
 */
public actual object AuthenticationCommand : AbstractRedisCommand<AuthenticationContext, String>() {
    @Suppress("NOTHING_TO_INLINE")
    private inline fun error(): RedisCommandFailedException = RedisCommandFailedException
        .Default(this::class, "AUTH")

    @OptIn(ExperimentalLettuceCoroutinesApi::class)
    override suspend fun execute(context: AuthenticationContext): Either<String, RedisCommandFailedException> {
        return if (context.username != null) {
            context.client.commands?.auth(context.username, context.password).either(::error)
        } else {
            context.client.commands?.auth(context.password).either(::error)
        }
    }
}

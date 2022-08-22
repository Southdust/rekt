package org.hexalite.rekt.core.command

import kotlinx.coroutines.await
import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.rekt.core.js.external.NodeRedisClientAuthOptions
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

    override suspend fun execute(context: AuthenticationContext): Either<String, RedisCommandFailedException> {
        val options = Any().unsafeCast<NodeRedisClientAuthOptions>().apply {
            username = context.username
            password = context.password.toString()
        }
        return context.client.delegate?.auth(options)?.await().either(::error)
    }
}

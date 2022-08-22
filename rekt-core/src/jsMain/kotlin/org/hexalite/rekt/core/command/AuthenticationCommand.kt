package org.hexalite.rekt.core.command

import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either

/**
 * A command to authenticate to the Redis server using a [password][AuthenticationContext.password], and an
 * [username][AuthenticationContext.username] if provided.
 * @author FromSyntax
 */
public actual object AuthenticationCommand : AbstractRedisCommand<AuthenticationContext, String>() {
    override suspend fun execute(context: AuthenticationContext): Either<String, RedisCommandFailedException> {
        TODO("Not yet implemented")
    }
}

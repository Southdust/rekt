package org.hexalite.rekt.core.command

import org.hexalite.rekt.core.RedisClient

/**
 * The context required to instruct the client to authenticate.
 * @author FromSyntax
 */
public data class AuthenticationContext(
    override val client: RedisClient,
    val username: String? = null,
    val password: CharSequence
) : AbstractRedisContext()

/**
 * A command to authenticate to the Redis server using a [password][AuthenticationContext.password], and an
 * [username][AuthenticationContext.username] if provided.
 * @author FromSyntax
 */
public expect object AuthenticationCommand : AbstractRedisCommand<AuthenticationContext, String>

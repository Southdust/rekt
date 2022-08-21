package org.hexalite.rekt.core.command

import org.hexalite.rekt.core.RedisClient

/**
 * A generic type for which context data is required to execute an implemented command.
 * @author FromSyntax
 */
public abstract class AbstractRedisContext {
    /**
     * The [client][RedisClient] this command context is bound to.
     */
    public abstract val client: RedisClient
}
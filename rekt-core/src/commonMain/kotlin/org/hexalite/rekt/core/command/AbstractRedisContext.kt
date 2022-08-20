package org.hexalite.rekt.core.command

import org.hexalite.rekt.core.RedisClient

/**
 * A generic type for which context data is required to execute an implemented command.
 * @author FromSyntax
 */
abstract class AbstractRedisContext {
    abstract val client: RedisClient
}
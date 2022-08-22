package org.hexalite.rekt.core.command

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import org.hexalite.rekt.core.RedisClient

/**
 * The context required to instruct the client to find an entry.
 * @author FromSyntax
 * @author Gabriel
 */
public data class RetrieveSingleEntryContext<T>(
    override val client: RedisClient,
    val key: String,
    val deserializer: DeserializationStrategy<T>
): AbstractRedisContext()

/**
 * A command to retrieve a specific value at the given [key][RetrieveSingleEntryContext.key].
 *
 * @author FromSyntax
 * @author Gabriel
 */
public expect object RetrieveSingleEntryCommand: AbstractRedisCommand<RetrieveSingleEntryContext<Any>, Any?>

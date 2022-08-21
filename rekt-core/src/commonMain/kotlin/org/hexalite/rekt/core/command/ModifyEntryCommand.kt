package org.hexalite.rekt.core.command

import kotlinx.serialization.KSerializer
import org.hexalite.rekt.core.RedisClient

/**
 * The context required to instruct the client to modify an entry.
 * @author FromSyntax
 * @author Gabriel
 */
public data class ModifyEntryContext<T>(
    override val client: RedisClient,
    val key: String,
    val value: T,
    val serializer: KSerializer<T>
) : AbstractRedisContext()

/**
 * A command to modify a specific value at the given [key][RetrieveSingleEntryContext.key].
 *
 * @author FromSyntax
 * @author Gabriel
 */
public expect object ModifyEntryCommand : AbstractRedisCommand<ModifyEntryContext<Any>, Any?>


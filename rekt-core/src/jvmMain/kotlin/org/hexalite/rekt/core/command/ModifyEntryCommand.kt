package org.hexalite.rekt.core.command

import io.lettuce.core.ExperimentalLettuceCoroutinesApi
import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either

/**
 * A command to modify a specific value at the given [key][RetrieveSingleEntryContext.key]. Returns the previous value
 * at the given key.
 *
 * @author FromSyntax
 * @author Gabriel
 */

public actual object ModifyEntryCommand : AbstractRedisCommand<ModifyEntryContext<Any>, Any?>() {
    @OptIn(ExperimentalLettuceCoroutinesApi::class)
    override suspend fun execute(context: ModifyEntryContext<Any>): Either<Any?, RedisCommandFailedException> {
        val data = context.client
            .configuration
            .dispatching
            .serializationFormat
            .encodeToString(context.serializer, context.value)
        val value = context.client.commands().left().find(context.key, context.serializer).leftOrNull()
        return context.client.commands?.set(context.key, data)?.run { Either.left(value) }
            ?: Either.right(RedisCommandFailedException.Custom("Failed to execute SET command."))
    }
}
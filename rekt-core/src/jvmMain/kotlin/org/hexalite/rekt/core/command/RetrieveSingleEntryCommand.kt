package org.hexalite.rekt.core.command

import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either


/**
 * A command to modify a specific value at the given [key][RetrieveSingleEntryContext.key].
 *
 * @author FromSyntax
 * @author Gabriel
 */
public actual object RetrieveSingleEntryCommand : AbstractRedisCommand<RetrieveSingleEntryContext<Any>, Any>() {
    override suspend fun execute(context: RetrieveSingleEntryContext<Any>): Either<Any, RedisCommandFailedException> {
        val value = context.client.commands?.get(context.key)
            ?: return Either.right(RedisCommandFailedException.Custom("Failed to execute GET command."))
        return Either.left(context.client
            .configuration
            .dispatching
            .serializationFormat
            .decodeFromString(context.deserializer, value))
    }
}
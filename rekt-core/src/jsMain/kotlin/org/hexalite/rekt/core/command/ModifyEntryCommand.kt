package org.hexalite.rekt.core.command

import kotlinx.coroutines.await
import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either
import org.hexalite.stronghold.data.functional.Either.Companion.either

/**
 * A command to modify a specific value at the given [key][RetrieveSingleEntryContext.key].
 *
 * @author FromSyntax
 * @author Gabriel
 */
public actual object ModifyEntryCommand : AbstractRedisCommand<ModifyEntryContext<Any>, Any?>() {
    @Suppress("NOTHING_TO_INLINE")
    private inline fun error(): RedisCommandFailedException = RedisCommandFailedException
        .Default(this::class, "SET + GET")

    override suspend fun execute(context: ModifyEntryContext<Any>): Either<Any?, RedisCommandFailedException> {
        val format = context.client.configuration.dispatching.serializationFormat
        val data = format.encodeToString(context.serializer, context.value)
        return context.client.commands()
            .mapLeft { it.find(context.key, context.previousValueSerializer).leftOrNull() }
            .takeRight { context.client.delegate?.set(context.key, data)?.await().either(::error) }
    }
}
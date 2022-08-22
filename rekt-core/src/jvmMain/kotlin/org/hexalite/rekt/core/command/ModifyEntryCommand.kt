package org.hexalite.rekt.core.command

import io.lettuce.core.ExperimentalLettuceCoroutinesApi
import kotlinx.serialization.decodeFromString
import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either
import org.hexalite.stronghold.data.functional.Either.Companion.either

/**
 * A command to modify a specific value at the given [key][RetrieveSingleEntryContext.key]. Returns the previous value
 * at the given key.
 *
 * @author FromSyntax
 * @author Gabriel
 */
public actual object ModifyEntryCommand : AbstractRedisCommand<ModifyEntryContext<Any>, Any?>() {
    @Suppress("NOTHING_TO_INLINE")
    private inline fun error(): RedisCommandFailedException = RedisCommandFailedException
        .Default(this::class, "SET + GET")

    @OptIn(ExperimentalLettuceCoroutinesApi::class)
    override suspend fun execute(context: ModifyEntryContext<Any>): Either<Any?, RedisCommandFailedException> {
        val format = context.client.configuration.dispatching.serializationFormat
        val data = format.encodeToString(context.serializer, context.value)
        return context.client.commands?.setGet(context.key, data).either(::error)
            .mapLeft { format.decodeFromString(context.previousValueSerializer, it) }
    }
}
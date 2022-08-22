package org.hexalite.rekt.core.command

import io.lettuce.core.ExperimentalLettuceCoroutinesApi
import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either
import org.hexalite.stronghold.data.functional.Either.Companion.either

/**
 * A command to modify a specific value at the given [key][RetrieveSingleEntryContext.key].
 *
 * @author FromSyntax
 * @author Gabriel
 */
public actual object RetrieveSingleEntryCommand : AbstractRedisCommand<RetrieveSingleEntryContext<Any>, Any?>() {
    @Suppress("NOTHING_TO_INLINE")
    private inline fun error(): RedisCommandFailedException = RedisCommandFailedException
        .Default(this::class, "GET", "Value is not present or something unexpected occurred.")

    @OptIn(ExperimentalLettuceCoroutinesApi::class)
    override suspend fun execute(context: RetrieveSingleEntryContext<Any>): Either<Any?, RedisCommandFailedException> {
        return context.client.commands?.get(context.key).either(::error)
            .mapLeft {
                context.client
                    .configuration
                    .dispatching
                    .serializationFormat
                    .decodeFromString(context.deserializer, it)
            }
    }
}
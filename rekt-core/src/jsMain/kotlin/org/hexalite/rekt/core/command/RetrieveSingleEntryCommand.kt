package org.hexalite.rekt.core.command

import kotlinx.coroutines.await
import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either
import org.hexalite.stronghold.data.functional.Either.Companion.either

/**
 * A command to retrieve a specific value at the given [key][RetrieveSingleEntryContext.key].
 *
 * @author FromSyntax
 * @author Gabriel
 */
public actual object RetrieveSingleEntryCommand : AbstractRedisCommand<RetrieveSingleEntryContext<Any>, Any?>() {
    @Suppress("NOTHING_TO_INLINE")
    private inline fun error(): RedisCommandFailedException = RedisCommandFailedException
        .Default(this::class, "GET", "Value is not present or something unexpected occurred.")

    override suspend fun execute(context: RetrieveSingleEntryContext<Any>): Either<Any?, RedisCommandFailedException> {
        return context.client.delegate?.get(context.key)?.await().either(::error)
            .mapLeft {
                context.client
                    .configuration
                    .dispatching
                    .serializationFormat
                    .decodeFromString(context.deserializer, it)
            }
    }
}
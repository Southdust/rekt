package org.hexalite.rekt.core.command

import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import org.hexalite.rekt.core.RedisClient
import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either
import kotlin.jvm.JvmInline

/**
 * A convenient wrapper for the most common Redis commands with direct support for kotlinx.serialization. This can
 * be easily accessed by a [RedisClient] at an active state.
 * @author FromSyntax
 * @author Gabriel
 */
@Suppress("UNCHECKED_CAST")
@JvmInline
public value class RedisCommandsScope(public val client: RedisClient) {
    /**
     * Instructs the [client] to dispatch a command to its platform-specific underlying Redis library.
     * @param command
     * @param context
     */
    public suspend fun <T : AbstractRedisContext, R> dispatch(
        command: AbstractRedisCommand<T, R>,
        context: T
    ): Either<R, RedisCommandFailedException> = withTimeoutOrNull(client.configuration().dispatching.timeout) {
        try {
            command.execute(context)
        } catch (exception: Exception) {
            Either.right(RedisCommandFailedException.Exception(exception))
        }
    } ?: Either.right(RedisCommandFailedException.TimedOut(command::class))

    /**
     * Dispatches a [RetrieveSingleEntryCommand] in order to retrieve an entry from the Redis map-like storage.
     * @param key
     * @param deserializer
     */
    public suspend inline fun <T : Any> find(
        key: String,
        deserializer: DeserializationStrategy<T>
    ): Either<T, RedisCommandFailedException> =
        dispatch(
            RetrieveSingleEntryCommand,
            RetrieveSingleEntryContext(client, key, deserializer) as RetrieveSingleEntryContext<Any>
        ).mapLeft { it as T }

    /**
     * Dispatches a [ModifyEntryCommand] in order to modify an entry at the Redis map-like storage.
     * @returns The previous set at this entry or null.
     * @param key
     * @param value
     * @param serializer
     */
    public suspend inline fun <T : Any> modify(
        key: String,
        value: T,
        serializer: KSerializer<T>
    ): Either<T, RedisCommandFailedException> = dispatch(
        ModifyEntryCommand,
        ModifyEntryContext(client, key, value, serializer) as ModifyEntryContext<Any>
    ).mapLeft { it as T }
}
@file:JvmName("CommandScopeExt")

package org.hexalite.rekt.core.extension

import kotlinx.serialization.serializer
import org.hexalite.rekt.core.command.ModifyEntryCommand
import org.hexalite.rekt.core.command.RedisCommandsScope
import org.hexalite.rekt.core.command.RetrieveSingleEntryCommand
import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either
import kotlin.jvm.JvmName

/**
 * Dispatches a [RetrieveSingleEntryCommand] in order to retrieve an entry from the Redis map-like storage.
 * @param key
 */
public suspend inline fun <reified T : Any> RedisCommandsScope.find(key: String): Either<T, RedisCommandFailedException> =
    find(key, serializer())

/**
 * Dispatches a [ModifyEntryCommand] in order to modify an entry at the Redis map-like storage.
 * @returns The previous set at this entry or null.
 * @param key
 * @param value
 */
public suspend inline fun <reified T : Any> RedisCommandsScope.modify(
    key: String,
    value: T
): Either<T, RedisCommandFailedException> = modify(key, value, serializer())

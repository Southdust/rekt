package org.hexalite.rekt.core.command

import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either

/**
 * A command to modify a specific value at the given [key][RetrieveSingleEntryContext.key].
 *
 * @author FromSyntax
 * @author Gabriel
 */
public actual object ModifyEntryCommand : AbstractRedisCommand<ModifyEntryContext<Any>, Any?>() {
    override suspend fun execute(context: ModifyEntryContext<Any>): Either<Any?, RedisCommandFailedException> {
        TODO("Not yet implemented")
    }
}
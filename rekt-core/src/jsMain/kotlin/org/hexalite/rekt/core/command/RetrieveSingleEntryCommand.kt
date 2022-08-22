package org.hexalite.rekt.core.command

import org.hexalite.rekt.core.exception.RedisCommandFailedException
import org.hexalite.stronghold.data.functional.Either

/**
 * A command to retrieve a specific value at the given [key][RetrieveSingleEntryContext.key].
 *
 * @author FromSyntax
 * @author Gabriel
 */
public actual object RetrieveSingleEntryCommand : AbstractRedisCommand<RetrieveSingleEntryContext<Any>, Any?>() {
    override suspend fun execute(context: RetrieveSingleEntryContext<Any>): Either<Any?, RedisCommandFailedException> {
        TODO("Not yet implemented")
    }
}
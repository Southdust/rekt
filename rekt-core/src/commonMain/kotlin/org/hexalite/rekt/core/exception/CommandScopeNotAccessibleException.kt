package org.hexalite.rekt.core.exception

/**
 * An exception thrown (or bound to the right) to indicate that the command scope could not be accessible. There
 * are many reasons to this, for example:
 * * [Disconnected] - Cannot access command scope while the Redis client connection is not active.
 * @author FromSyntax
 */
public sealed class CommandScopeNotAccessibleException(override val message: String, override val cause: Throwable? = null) :
    RuntimeException(message, cause) {
    public object Disconnected : CommandScopeNotAccessibleException("Cannot access command scope while disconnected.")
}

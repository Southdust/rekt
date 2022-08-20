package org.hexalite.rekt.core.exception

/**
 * An exception thrown (or bound to the right) to indicate the connection couldn't be made active. The [cause] is
 * usually added to the platform this is running to show from what library this error is coming from exactly.
 * @author FromSyntax
 */
data class ConnectionFailedException(override val message: String, override val cause: Throwable? = null) :
    RuntimeException(message, cause)

package org.hexalite.rekt.core.exception

/**
 * An exception thrown (or bound to the right) to indicate the connection couldn't be made inactive. The [cause] is
 * usually added to the platform this is running to show from what library this error is coming from exactly. There are
 * other reasons that could make this error happen:
 * * [NotConnected] - Can't close a connection that isn't open
 * @author FromSyntax
 */
public sealed class DisconnectionFailedException(override val message: String, override val cause: Throwable? = null) :
    RuntimeException(message, cause) {
    public object NotConnected : DisconnectionFailedException("Can't close a connection that isn't open.")

    public data class Custom(override val message: String, override val cause: Throwable? = null) :
        DisconnectionFailedException(message, cause)
}

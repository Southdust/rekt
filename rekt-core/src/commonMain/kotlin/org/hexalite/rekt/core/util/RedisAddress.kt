package org.hexalite.rekt.core.util

/**
 * A sealed interface representing all possible ways to represent a connectable address with Redis
 * clients.
 * @author FromSyntax
 */
sealed interface RedisAddress {
    companion object {
        val Localhost = Conventional("127.0.0.1", 6379)
    }

    override fun toString(): String

    /**
     * The default and most common address type, followed by a [hostname] then a [port] separated by a colon and
     * using the `redis://` protocol.
     * @author FromSyntax
     */
    data class Conventional(val hostname: String, val port: Short): RedisAddress {
        override fun toString() = "$hostname:$port"
    }

    /**
     * An uncommon way to use Redis, but it is almost guaranteed to be faster in most cases, and it is not available
     * to be used remotely. This is only available on Unix systems.
     * @author FromSyntax
     */
    data class UnixDomainSocket(val path: String): RedisAddress {
        override fun toString(): String = path
    }
}
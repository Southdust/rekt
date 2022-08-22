package org.hexalite.rekt.core.util

import org.hexalite.rekt.core.annotation.RedisDsl

/**
 * A sealed interface representing all possible ways to represent a connectable address with Redis
 * clients.
 * @author FromSyntax
 */
public sealed interface RedisAddress {
    public companion object {
        public val Localhost: RedisAddress.Conventional = Conventional("127.0.0.1", 6379)

        @Suppress("NOTHING_TO_INLINE")
        public inline operator fun invoke(
            hostname: String,
            port: Short,
            username: String? = null,
            password: String? = null,
            databaseIndex: Int = 0
        ): RedisAddress = Conventional(hostname, port, username, password, databaseIndex)

        @RedisDsl
        public inline operator fun invoke(builder: Conventional.() -> Unit): Conventional =
            Localhost.copy().apply(builder)
    }

    override fun toString(): String

    /**
     * The default and most common address type, followed by a [hostname] then a [port] separated by a colon and
     * using the `redis://` protocol.
     * @author FromSyntax
     */
    public data class Conventional(
        public var hostname: String,
        public var port: Short = 6379,
        public var username: String? = null,
        public var password: String? = null,
        public var databaseIndex: Int = 0
    ) : RedisAddress {
        override fun toString(): String = buildString {
            append("redis://")
            if (username != null) {
                append(username)
                if (password != null) {
                    append(":$password@")
                }
            } else if (password != null) {
                append("$password@")
            }
            append("$hostname:$port/$databaseIndex")
        }
    }

    /**
     * An unusual way to use Redis, but it is almost guaranteed to be faster in most cases, and it is not available
     * to be used remotely. This is only available on Unix systems.
     * @author FromSyntax
     */
    public data class UnixDomainSocket(public val path: String) : RedisAddress {
        override fun toString(): String = path
    }
}
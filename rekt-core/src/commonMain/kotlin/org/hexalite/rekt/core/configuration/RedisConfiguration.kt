package org.hexalite.rekt.core.configuration

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.StringFormat
import kotlinx.serialization.cbor.Cbor
import org.hexalite.rekt.core.RedisClient
import org.hexalite.rekt.core.annotation.RedisDsl
import org.hexalite.rekt.core.serialization.HexCbor
import org.hexalite.rekt.core.util.RedisAddress
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * A data class representing all configurations used to connect, initialize or dispatch commands
 * in a [RedisClient].
 *
 * @param connection Specific properties related to the connection.
 * @param dispatching Specific properties related to command dispatching.
 */
public data class RedisConfiguration(
    val connection: ConnectionSettings = ConnectionSettings(),
    val dispatching: DispatchingConfiguration = DispatchingConfiguration(),
    var enableLogging: Boolean = true,
) {
    @RedisDsl
    public inline fun dispatching(builder: DispatchingConfiguration.() -> Unit) {
        dispatching.apply(builder)
    }

    @RedisDsl
    public inline fun connection(builder: ConnectionSettings.() -> Unit) {
        connection.apply(builder)
    }
}

/**
 * A data class representing specific configurations to initialize a [RedisClient] connection.
 * @param address The address pointing to the Redis server.
 * @param password The password (`'requirepass'`) required to establish a connection to this [address].
 * @param timeout The duration which the connection would expire if it is not established on this interpublic val.
 * @author FromSyntax
 */
public data class ConnectionSettings(
    var address: RedisAddress = RedisAddress.Localhost,
    var timeout: Duration = 30.seconds,
) {
    @RedisDsl
    public inline fun address(builder: RedisAddress.Conventional.() -> Unit): RedisAddress {
        address = RedisAddress(builder)
        return address
    }
}

/**
 * A data class representing specific configurations to dispatch commands to a [RedisClient] connection.
 * @param timeout The duration which command dispatches would expire, if its job is not finished yet.
 * @author FromSyntax
 */
@OptIn(ExperimentalSerializationApi::class)
public data class DispatchingConfiguration(
    var timeout: Duration = 30.seconds,
    var serializationFormat: StringFormat = HexCbor(Cbor { ignoreUnknownKeys = true })
)

package org.hexalite.rekt.core.configuration

import org.hexalite.rekt.core.RedisClient
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
data class RedisConfiguration(
    val connection: ConnectionSettings = ConnectionSettings(),
    val dispatching: DispatchingConfiguration = DispatchingConfiguration(),
)

/**
 * A data class representing specific configurations to initialize a [RedisClient] connection.
 * @param address The address pointing to the Redis server.
 * @param password The password (`'requirepass'`) required to establish a connection to this [address].
 * @param timeout The duration which the connection would expire if it is not established on this interval.
 * @author FromSyntax
 */
data class ConnectionSettings(
    var address: RedisAddress = RedisAddress.Localhost,
    var password: String? = null,
    var timeout: Duration = 30.seconds,
)

/**
 * A data class representing specific configurations to dispatch commands to a [RedisClient] connection.
 * @param timeout The duration which command dispatches would expire, if its job is not finished yet.
 * @author FromSyntax
 */
data class DispatchingConfiguration(
    var timeout: Duration = 30.seconds,
)

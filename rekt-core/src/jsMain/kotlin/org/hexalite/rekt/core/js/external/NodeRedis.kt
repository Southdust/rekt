package org.hexalite.rekt.core.js.external

import kotlin.js.Promise

@JsModule("redis")
@JsNonModule
@JsName("createClient")
public external fun createNodeRedisClient(options: NodeRedisClientOptions = definedExternally): NodeRedisClient

@JsModule("redis")
@JsName("RedisClientOptions")
public external interface NodeRedisClientOptions {
    public var url: String
}

@JsModule("redis")
@JsName("AuthOptions")
public external interface NodeRedisClientAuthOptions {
    public var username: String?
    public var password: String
}

@JsModule("redis")
@JsName("RedisClient")
public external class NodeRedisClient {
    public fun on(event: String, listener: (item: dynamic) -> Unit)

    public fun connect(): Promise<Unit>

    public fun disconnect(): Promise<Unit>

    public fun set(key: String, value: String): Promise<Unit>

    public fun get(key: String): Promise<String?>

    public fun auth(options: NodeRedisClientAuthOptions): Promise<String>

    public fun sendCommand(args: Array<String>): dynamic
}

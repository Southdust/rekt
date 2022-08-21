package org.hexalite.rekt.core.serialization

import kotlinx.serialization.*
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.modules.SerializersModule
import kotlin.jvm.JvmInline

/**
 * A utility class to represent CBOR (de)serialization as a hexadecimal-based [StringFormat].
 * @author FromSyntax
 */
@JvmInline
@OptIn(ExperimentalSerializationApi::class)
public value class HexCbor(public val delegate: Cbor) : StringFormat {
    override val serializersModule: SerializersModule
        get() = delegate.serializersModule

    override fun <T> decodeFromString(deserializer: DeserializationStrategy<T>, string: String): T =
        delegate.decodeFromHexString(deserializer, string)

    override fun <T> encodeToString(serializer: SerializationStrategy<T>, value: T): String =
        delegate.encodeToHexString(serializer, value)
}
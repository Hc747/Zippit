package com.mercury.zippit.extensions

import io.netty.buffer.ByteBuf

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */

private val DEFAULT_CHARSET = Charsets.UTF_8

fun ByteBuf.writeString(message: String): ByteBuf {
    val data = message.toByteArray(DEFAULT_CHARSET)
    return this.writeShort(data.size).writeBytes(data)
}

fun ByteBuf.readString(): String {
    val length = readUnsignedShort()
    if (!isReadable(length))
        throw IllegalStateException("Expected length: $length, actual length: ${readableBytes()}")
    val data = ByteArray(length)
    readBytes(data)
    return String(data, DEFAULT_CHARSET)
}

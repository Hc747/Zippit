package com.mercury.zippit.net.message

import io.netty.buffer.ByteBuf

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */
enum class MessageLength {

    FIXED,
    BYTE,
    SHORT,
    ;
}

abstract class OutboundMessage(private val size: MessageLength) {

    abstract fun encode(): ByteBuf

    fun writeTo(buffer: ByteBuf) {
        val data = encode()

        //TODO: ensure that writer index doesn't need to be writer index + 1

        if (size != MessageLength.FIXED) {
            if (size == MessageLength.BYTE)
                buffer.writeByte(data.writerIndex())
            else
                buffer.writeShort(data.writerIndex())
        }

        buffer.writeBytes(data)
    }

}
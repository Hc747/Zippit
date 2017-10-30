package com.mercury.zippit.net.codec.handshake

import com.mercury.zippit.configuration.Version
import com.mercury.zippit.net.message.MessageLength
import com.mercury.zippit.net.message.OutboundMessage
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
internal data class HandshakeRequest(private val version: Version, private val endpoint: HandshakeRequestEndpoint): OutboundMessage(MessageLength.FIXED) {

    private val timestamp: Long = System.currentTimeMillis()

    override fun encode(): ByteBuf {
        val buffer = Unpooled.buffer(13, 13)

        buffer.writeLong(timestamp)
        buffer.writeShort(version.major)
        buffer.writeShort(version.minor)
        buffer.writeByte(endpoint.ordinal)

        return buffer
    }

}
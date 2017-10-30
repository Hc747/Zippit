package com.mercury.zippit.net.codec.login

import com.mercury.zippit.extensions.writeString
import com.mercury.zippit.net.message.MessageLength
import com.mercury.zippit.net.message.OutboundMessage
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
data class LoginRequest(private val username: String, private val password: String): OutboundMessage(MessageLength.SHORT) {

    private val timestamp: Long = System.currentTimeMillis()

    override fun encode(): ByteBuf {
        val buffer = Unpooled.buffer()

        buffer.writeLong(timestamp)
        buffer.writeString(username)
        buffer.writeString(password)

        return buffer
    }

}
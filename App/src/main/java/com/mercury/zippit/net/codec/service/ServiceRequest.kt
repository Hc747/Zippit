package com.mercury.zippit.net.codec.service

import com.mercury.zippit.configuration.Version
import com.mercury.zippit.extensions.writeString
import com.mercury.zippit.net.message.OutboundMessage
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
abstract class ServiceRequest(private val version: Version, private val endpoint: ServiceEndpoint, private val username: String, private val password: String): OutboundMessage {

    private val timestamp: Long = System.currentTimeMillis()

    override fun encode(): ByteBuf {
        val buffer = Unpooled.buffer(13, 13)

        buffer.writeLong(timestamp)
        buffer.writeShort(version.major)
        buffer.writeShort(version.minor)
        buffer.writeByte(endpoint.ordinal)
        buffer.writeString(username)
        buffer.writeString(password)

        return buffer
    }

}

class LoginServiceRequest(version: Version, username: String, password: String) : ServiceRequest(version, ServiceEndpoint.LOGIN, username, password)
class RegistrationServiceRequest(version: Version, username: String, password: String) : ServiceRequest(version, ServiceEndpoint.REGISTRATION, username, password)
package com.mercury.zippit.net.codec.service

import com.mercury.zippit.configuration.Version
import com.mercury.zippit.extensions.readString
import com.mercury.zippit.persistence.sql.Database
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import java.util.logging.Logger

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */
class ServiceRequestDecoder(private val database: Database, private val version: Version) : ByteToMessageDecoder() {

    companion object {

        val logger = Logger.getLogger(ServiceRequestDecoder::class.java.simpleName)

    }

    override fun decode(context: ChannelHandlerContext, buffer: ByteBuf, out: MutableList<Any>) {
        val timestamp = buffer.readLong()

        val version = Version(buffer.readShort().toInt(), buffer.readShort().toInt())

        val endpoint = ServiceEndpoint.lookup(buffer.readUnsignedByte().toInt())

        val username = buffer.readString()

        val password = buffer.readString()

        logger.info("Timestamp: $timestamp, Version: $version, Endpoint: $endpoint, Username: $username, Password: $password")

        //context.channel().writeAndFlush(HandshakeRequestHeader(timestamp, version, endpoint))
    }

}
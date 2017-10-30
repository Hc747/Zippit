package com.mercury.zippit.net.codec.handshake

import com.mercury.zippit.configuration.Version
import com.mercury.zippit.net.codec.handshake.HandshakeConstants.FAILURE
import com.mercury.zippit.net.codec.handshake.HandshakeConstants.INVALID_SERVICE
import com.mercury.zippit.net.codec.handshake.HandshakeConstants.OUTDATED
import com.mercury.zippit.utilities.ByteBufUtilities.writeString
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import java.util.logging.Logger

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */
class HandshakeResponseEncoder(private val version: Version) : MessageToByteEncoder<HandshakeRequest>(HandshakeRequest::class.java) {

    companion object {

        private val logger = Logger.getLogger(HandshakeResponseEncoder::class.java.simpleName)

    }

    override fun encode(context: ChannelHandlerContext, request: HandshakeRequest, response: ByteBuf) {

        logger.info("Processing request: $request at: ${System.currentTimeMillis()}")

        if (version != request.version) {
            fail(response, OUTDATED)
            return
        }

        if (request.endpoint == null) {
            fail(response, INVALID_SERVICE)
            return
        }

        request.endpoint.handle(context)
        response.writeByte(request.endpoint.ordinal)
    }

    private fun fail(buffer: ByteBuf, reason: String) {
        //TODO:
        buffer.writeByte(FAILURE)
        writeString(buffer, reason)
    }

}
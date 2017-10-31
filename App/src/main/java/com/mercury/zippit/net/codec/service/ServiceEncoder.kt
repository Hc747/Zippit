package com.mercury.zippit.net.codec.service

import com.mercury.zippit.net.message.OutboundMessage
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import java.util.logging.Logger

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
class ServiceEncoder : MessageToByteEncoder<OutboundMessage>(OutboundMessage::class.java) {

    companion object {

        val logger = Logger.getLogger(ServiceEncoder::class.java.simpleName)

    }

    override fun encode(context: ChannelHandlerContext, message: OutboundMessage, out: ByteBuf) {
        logger.info(message.toString())

        context.write(message.encode())
    }

}

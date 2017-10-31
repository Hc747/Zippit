package com.mercury.zippit.net.codec

import com.mercury.zippit.net.message.OutboundMessage
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder
import java.util.logging.Logger

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
class OutboundMessageEncoder : MessageToMessageEncoder<OutboundMessage>(OutboundMessage::class.java) {

    companion object {

        val logger = Logger.getLogger(OutboundMessageEncoder::class.java.simpleName)

    }

    override fun encode(context: ChannelHandlerContext, message: OutboundMessage, out: MutableList<Any>) {
        logger.info(message.toString())

        out.add(message.encode())
    }

}

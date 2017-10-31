package com.mercury.zippit.net.codec.service

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import java.util.logging.Logger

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
class ServiceDecoder : ByteToMessageDecoder() {

    companion object {

        private val logger = Logger.getLogger(ServiceDecoder::class.java.simpleName)

    }

    override fun decode(context: ChannelHandlerContext, buffer: ByteBuf, out: List<Any>) {
        /*
        if (!buffer.isReadable) return

        val ordinal = buffer.readByte().toInt()

        val endpoint = ServiceEndpoint.lookup(ordinal)

        if (endpoint == null) {
            logger.info(buffer.readString()) //TODO: length check
            return
        }

        logger.info(endpoint.toString())

        endpoint.handle(context)*/
        logger.warning("TODO")
    }

}

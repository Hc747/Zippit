package com.mercury.zippit.net.codec.registration

import com.mercury.zippit.persistence.sql.Database
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

import java.util.logging.Logger

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
class RegistrationResponseEncoder(private val database: Database) : MessageToByteEncoder<Any>() {//TODO: specified type

    override fun encode(ctx: ChannelHandlerContext, msg: Any, out: ByteBuf) {
        logger.info("TODO: implement")
    }

    companion object {

        private val logger = Logger.getLogger(RegistrationResponseEncoder::class.java.simpleName)
    }

}

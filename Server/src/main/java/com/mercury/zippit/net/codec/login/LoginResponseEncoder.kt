package com.mercury.zippit.net.codec.login

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import java.util.logging.Logger

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 25/10/17
 */
class LoginResponseEncoder : MessageToByteEncoder<LoginRequest>(LoginRequest::class.java) {

    companion object {

        private val logger = Logger.getLogger(LoginResponseEncoder::class.java.simpleName)

    }

    override fun encode(context: ChannelHandlerContext, request: LoginRequest, response: ByteBuf) {

        logger.info("Processing request: $request at: ${System.currentTimeMillis()}")

    }

}

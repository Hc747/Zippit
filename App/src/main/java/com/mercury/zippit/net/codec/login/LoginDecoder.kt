package com.mercury.zippit.net.codec.login

import com.mercury.zippit.extensions.readString
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
class LoginDecoder : ByteToMessageDecoder() {

    override fun decode(context: ChannelHandlerContext, buffer: ByteBuf, out: List<Any>) {
        println(buffer.readString()) //TODO:
    }

}

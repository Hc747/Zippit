package com.mercury.zippit.net.codec.login

import com.mercury.zippit.utilities.ByteBufUtilities.readString
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 25/10/17
 */
class LoginRequestDecoder : ByteToMessageDecoder() {

    //TODO: security
    //TODO: length checks
    //TODO: state

    override fun decode(context: ChannelHandlerContext, buffer: ByteBuf, out: List<Any>) {
        val timestamp = buffer.readLong()
        val username = readString(buffer)
        val password = readString(buffer)

        context.channel().writeAndFlush(LoginRequest(timestamp, username, password))
    }

}

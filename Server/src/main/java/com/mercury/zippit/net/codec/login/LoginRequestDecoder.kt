package com.mercury.zippit.net.codec.login

import com.mercury.zippit.extensions.readString
import com.mercury.zippit.net.codec.login.LoginConstants.HEADER_LENGTH
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 25/10/17
 */
class LoginRequestDecoder : ByteToMessageDecoder() {

    //TODO: security / validation
    //TODO: length checks
    //TODO: state

    override fun decode(context: ChannelHandlerContext, buffer: ByteBuf, out: MutableList<Any>) {
        if (!buffer.isReadable(HEADER_LENGTH)) return

        val length = buffer.readUnsignedShort()

        if (!buffer.isReadable(length)) {
            buffer.readerIndex(buffer.readerIndex() - 2)
            return
        }

        val timestamp = buffer.readLong()
        val username = buffer.readString()
        val password = buffer.readString()

        context.channel().writeAndFlush(LoginRequest(timestamp, username, password))
    }

}

package com.mercury.zippit.net.codec.handshake

import com.mercury.zippit.configuration.Version
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */
class HandshakeRequestDecoder : ByteToMessageDecoder() {

    override fun decode(context: ChannelHandlerContext, buffer: ByteBuf, out: MutableList<Any>) {
        if (!buffer.isReadable(HandshakeConstants.BLOCK_LENGTH)) return

        val timestamp = buffer.readLong()
        val version = Version(buffer.readShort().toInt(), buffer.readShort().toInt())
        val endpoint = HandshakeRequestEndpoint.lookup(buffer.readUnsignedByte().toInt())

        context.channel().writeAndFlush(HandshakeRequest(timestamp, version, endpoint))
    }

}
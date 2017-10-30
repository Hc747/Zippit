package com.mercury.zippit.net

import com.mercury.zippit.net.codec.handshake.HandshakeDecoder
import com.mercury.zippit.net.codec.handshake.HandshakeEncoder
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
class ZippitChannelInitialiser(private val handler: ZippitHandler) : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        val pipeline = ch.pipeline()

        val decoder = HandshakeDecoder()
        val encoder = HandshakeEncoder()

        pipeline.addLast(HandshakeDecoder::class.java.simpleName, decoder)
        pipeline.addLast(HandshakeEncoder::class.java.simpleName, encoder)
        pipeline.addLast(ZippitHandler::class.java.simpleName, handler)
    }
}

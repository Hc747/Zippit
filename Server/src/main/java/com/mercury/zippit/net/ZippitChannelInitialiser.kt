package com.mercury.zippit.net

import com.mercury.zippit.configuration.Version
import com.mercury.zippit.net.codec.handshake.HandshakeRequestDecoder
import com.mercury.zippit.net.codec.handshake.HandshakeResponseEncoder
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.timeout.IdleStateHandler

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */
class ZippitChannelInitialiser(private val handler: ZippitHandler, private val version: Version) : ChannelInitializer<SocketChannel>() {

    override fun initChannel(channel: SocketChannel) {
        val pipeline = channel.pipeline()

        val decoder = HandshakeRequestDecoder()
        val encoder = HandshakeResponseEncoder(version)
        val timeout = IdleStateHandler(NetworkConstants.IDLE_TIME, 0, 0)

        pipeline.addLast(HandshakeRequestDecoder::class.java.simpleName, decoder)
        pipeline.addLast(HandshakeResponseEncoder::class.java.simpleName, encoder)
        pipeline.addLast(IdleStateHandler::class.java.simpleName, timeout)
        pipeline.addLast(ZippitHandler::class.java.simpleName, handler)
    }

}
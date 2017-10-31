package com.mercury.zippit.net

import com.mercury.zippit.net.codec.OutboundMessageEncoder
import com.mercury.zippit.net.codec.service.ServiceDecoder
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
class ZippitChannelInitialiser(private val handler: ZippitHandler) : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        val pipeline = ch.pipeline()

        val decoder = ServiceDecoder()
        val encoder = OutboundMessageEncoder()

        pipeline.addLast(ProtobufVarint32FrameDecoder())
        pipeline.addLast(decoder)

        pipeline.addLast(ProtobufVarint32LengthFieldPrepender())
        pipeline.addLast(encoder)

        pipeline.addLast(handler)
    }
}

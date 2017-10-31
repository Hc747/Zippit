package com.mercury.zippit.net

import com.mercury.zippit.configuration.Version
import com.mercury.zippit.net.codec.OutboundMessageEncoder
import com.mercury.zippit.net.codec.service.ServiceRequestDecoder
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender
import io.netty.handler.timeout.IdleStateHandler

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */
class ZippitChannelInitialiser(private val handler: ZippitHandler, private val version: Version) : ChannelInitializer<SocketChannel>() {

    override fun initChannel(channel: SocketChannel) {
        val pipeline = channel.pipeline()

        val decoder = ServiceRequestDecoder(version)
        val encoder = OutboundMessageEncoder()
        val timeout = IdleStateHandler(NetworkConstants.IDLE_TIME, 0, 0)

        pipeline.addLast(ProtobufVarint32FrameDecoder())
        pipeline.addLast(decoder)

        pipeline.addLast(ProtobufVarint32LengthFieldPrepender())
        pipeline.addLast(encoder)

        pipeline.addLast(timeout)
        pipeline.addLast(handler)
    }

}
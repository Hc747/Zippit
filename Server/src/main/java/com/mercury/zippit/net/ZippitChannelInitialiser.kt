package com.mercury.zippit.net

import com.mercury.zippit.configuration.Version
import com.mercury.zippit.net.codec.OutboundMessageEncoder
import com.mercury.zippit.net.codec.service.ServiceRequestDecoder
import com.mercury.zippit.persistence.sql.Database
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
class ZippitChannelInitialiser(private val handler: ZippitHandler, private val database: Database, private val version: Version) : ChannelInitializer<SocketChannel>() {

    override fun initChannel(channel: SocketChannel) {
        val pipeline = channel.pipeline()

        val decoder = ServiceRequestDecoder(database, version)
        val encoder = OutboundMessageEncoder()
        val timeout = IdleStateHandler(NetworkConstants.IDLE_TIME, 0, 0)

        pipeline.addLast(ProtobufVarint32FrameDecoder::class.java.simpleName, ProtobufVarint32FrameDecoder())
        pipeline.addLast(ServiceRequestDecoder::class.java.simpleName, decoder)

        pipeline.addLast(ProtobufVarint32LengthFieldPrepender::class.java.simpleName, ProtobufVarint32LengthFieldPrepender())
        pipeline.addLast(OutboundMessageEncoder::class.java.simpleName, encoder)

        pipeline.addLast(IdleStateHandler::class.java.simpleName, timeout)
        pipeline.addLast(ZippitHandler::class.java.simpleName, handler)
    }

}
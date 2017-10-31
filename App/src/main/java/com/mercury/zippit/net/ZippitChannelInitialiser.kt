package com.mercury.zippit.net

import com.mercury.zippit.net.codec.service.ServiceDecoder
import com.mercury.zippit.net.codec.service.ServiceEncoder
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
        val encoder = ServiceEncoder()

        pipeline.addLast(ProtobufVarint32FrameDecoder::class.java.simpleName, ProtobufVarint32FrameDecoder())
        pipeline.addLast(ServiceDecoder::class.java.simpleName, decoder)

        pipeline.addLast(ProtobufVarint32LengthFieldPrepender::class.java.simpleName, ProtobufVarint32LengthFieldPrepender())
        pipeline.addLast(ServiceEncoder::class.java.simpleName, encoder)

        pipeline.addLast(ZippitHandler::class.java.simpleName, handler)
    }
}

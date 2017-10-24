package com.mercury.zippit.net.codec.handshake;

import com.mercury.zippit.net.codec.Service;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 24/10/17
 */
public final class HandshakeDecoder extends ByteToMessageDecoder {

	private static final Logger logger = Logger.getLogger(HandshakeDecoder.class.getSimpleName());

	@Override
	protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> out) {
		if (!buffer.isReadable())
			return;

		int id = buffer.readUnsignedByte();

		Service service = Service.get(id);

		logger.info(String.format("received request for service: %s, id: %d", service, id));

		switch (service) {
			case APP:
				//TODO: add encoder and decoder
				break;

			case UPDATE:
				//TODO: add encoder and decoder
				break;

			default:
				logger.info(String.format("Unexpected handshake request received: %d data: %s", id, buffer.readBytes(buffer.readableBytes())));
				return;
		}

		context.pipeline().remove(this);
		out.add(new HandshakeMessage(service));
	}

}

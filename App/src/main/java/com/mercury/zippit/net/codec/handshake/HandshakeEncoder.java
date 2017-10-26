package com.mercury.zippit.net.codec.handshake;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
public final class HandshakeEncoder extends MessageToByteEncoder<HandshakeRequest> {

	@Override
	protected void encode(ChannelHandlerContext context, HandshakeRequest request, ByteBuf out) {
		ByteBuf data = context.alloc().buffer(5);
		int version = (request.getVersionMajor() << 16) | request.getVersionMinor() & 0xFFFF;

		try {
			data.writeInt(version);
			data.writeByte(request.getServiceId());

			out.writeBytes(data);
		} finally {
			ReferenceCountUtil.release(data);
		}
	}

}

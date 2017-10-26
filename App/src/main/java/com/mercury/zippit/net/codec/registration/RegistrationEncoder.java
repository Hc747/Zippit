package com.mercury.zippit.net.codec.registration;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;
import utilities.ByteBufUtilities;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
public final class RegistrationEncoder extends MessageToByteEncoder<RegistrationRequest> {

	public RegistrationEncoder() {
		super(RegistrationRequest.class);
	}

	@Override
	protected void encode(ChannelHandlerContext context, RegistrationRequest request, ByteBuf out) {
		ByteBuf data = context.alloc().buffer();

		try {
			ByteBufUtilities.writeString(data, request.getUsername());
			ByteBufUtilities.writeString(data, request.getPassword());
			ByteBufUtilities.writeString(data, request.getEmail());

			out.writeBytes(data);
		} finally {
			ReferenceCountUtil.release(data);
		}
	}


}

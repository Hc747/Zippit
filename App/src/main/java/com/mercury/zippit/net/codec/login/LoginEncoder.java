package com.mercury.zippit.net.codec.login;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
public final class LoginEncoder extends MessageToByteEncoder<LoginRequest> {

	public LoginEncoder() {
		super(LoginRequest.class);
	}

	@Override
	protected void encode(ChannelHandlerContext context, LoginRequest request, ByteBuf out) {
		request.writeTo(out);
	}

}

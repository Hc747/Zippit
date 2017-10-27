package com.mercury.zippit.net.codec.login;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 25/10/17
 */
public final class LoginResponseEncoder extends MessageToByteEncoder<LoginRequest> {

	@Override
	protected void encode(ChannelHandlerContext context, LoginRequest request, ByteBuf response) {

	}

}

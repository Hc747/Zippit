package com.mercury.zippit.net.codec.login;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 25/10/17
 */
public final class LoginRequestDecoder extends ByteToMessageDecoder {

	//TODO: security

	@Override
	protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> out) {

	}

}

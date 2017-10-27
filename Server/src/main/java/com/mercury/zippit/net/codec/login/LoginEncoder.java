package com.mercury.zippit.net.codec.login;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.logging.Logger;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 25/10/17
 */
public final class LoginEncoder extends MessageToByteEncoder<Object> {//TODO: specified type

	private static final Logger logger = Logger.getLogger(LoginEncoder.class.getSimpleName());

	@Override
	protected void encode(ChannelHandlerContext context, Object o, ByteBuf byteBuf) {
		logger.info("TODO: implement");
	}

}

package com.mercury.zippit.net.codec.registration;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.logging.Logger;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
public final class RegistrationResponseEncoder extends MessageToByteEncoder<Object> {//TODO: specified type

	private static final Logger logger = Logger.getLogger(RegistrationResponseEncoder.class.getSimpleName());

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
		logger.info("TODO: implement");
	}

}

package com.mercury.zippit.net.codec.registration;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
public final class RegistrationRequestDecoder extends ByteToMessageDecoder {

	private static final Logger logger = Logger.getLogger(RegistrationRequestDecoder.class.getSimpleName());

	@Override
	protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> out) {
		logger.info("TODO: implement");
	}

}
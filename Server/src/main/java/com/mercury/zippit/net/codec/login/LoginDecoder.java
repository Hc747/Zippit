package com.mercury.zippit.net.codec.login;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 25/10/17
 */
public final class LoginDecoder extends ByteToMessageDecoder {

	private static final Logger logger = Logger.getLogger(LoginDecoder.class.getSimpleName());

	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
		logger.info("TODO");
	}

}

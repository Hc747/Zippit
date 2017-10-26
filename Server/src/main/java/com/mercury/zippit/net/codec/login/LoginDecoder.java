package com.mercury.zippit.net.codec.login;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import utilities.ByteBufUtilities;

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
	protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> out) {
		String username = ByteBufUtilities.readString(buffer);
		String password = ByteBufUtilities.readString(buffer);
		boolean reconnecting = buffer.readBoolean();

		logger.info(String.format("Username: %s, Password: %s, Reconnecting: %s", username, password, reconnecting));

		//TODO: add login response
	}

}

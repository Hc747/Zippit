package com.mercury.zippit.net.codec.handshake;

import com.mercury.zippit.utilities.ByteBufUtilities;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
public final class HandshakeDecoder extends ByteToMessageDecoder {

	private static final Logger logger = Logger.getLogger(HandshakeDecoder.class.getSimpleName());

	@Override
	protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> out) {
		if (!buffer.isReadable()) return;

		boolean success = buffer.readBoolean();

		logger.info(String.format("success: %s", success));

		if (!success) {
			logger.info(ByteBufUtilities.readString(buffer));
			return;
		}

		//TODO: change state to login / registration

	}

}

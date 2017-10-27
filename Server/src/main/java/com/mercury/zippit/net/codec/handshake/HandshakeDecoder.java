package com.mercury.zippit.net.codec.handshake;

import com.mercury.zippit.configuration.Version;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import static com.mercury.zippit.net.codec.handshake.HandshakeConstants.BLOCK_LENGTH;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
public final class HandshakeDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> out) {
		if (!buffer.isReadable(BLOCK_LENGTH)) return;

		Version remote = new Version(buffer.readShort(), buffer.readShort());
		HandshakeService service = HandshakeService.lookup(buffer.readUnsignedByte());

		out.add(new HandshakeRequest(remote, service));
	}

}

package com.mercury.zippit.net.codec.handshake;

import com.mercury.zippit.configuration.Version;
import com.mercury.zippit.utilities.ByteBufUtilities;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import static com.mercury.zippit.net.codec.handshake.HandshakeConstants.INVALID_SERVICE;
import static com.mercury.zippit.net.codec.handshake.HandshakeConstants.OUTDATED;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
public final class HandshakeResponseEncoder extends MessageToByteEncoder<HandshakeRequest> {

	private final Version version;

	public HandshakeResponseEncoder(Version version) {
		super(HandshakeRequest.class);
		this.version = version;
	}

	@Override
	protected void encode(ChannelHandlerContext context, HandshakeRequest request, ByteBuf response) {
		if (!version.equals(request.getVersion())) {
			fail(response, OUTDATED);
			return;
		}

		HandshakeRequestEndpoint endpoint = request.getEndpoint();

		if (endpoint == null) {
			fail(response, INVALID_SERVICE);
			return;
		}

		response.writeBoolean(true);
		endpoint.handle(context);
	}

	private void fail(ByteBuf buffer, String reason) {
		buffer.writeBoolean(false);
		ByteBufUtilities.writeString(buffer, reason);
	}

}

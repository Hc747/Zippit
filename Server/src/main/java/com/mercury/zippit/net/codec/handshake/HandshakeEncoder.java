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
public final class HandshakeEncoder extends MessageToByteEncoder<HandshakeRequest> {

	private final Version version;

	public HandshakeEncoder(Version version) {
		super(HandshakeRequest.class);
		this.version = version;
	}

	@Override
	protected void encode(ChannelHandlerContext context, HandshakeRequest request, ByteBuf out) {
		if (!version.equals(request.getVersion())) {
			fail(out, OUTDATED);
			return;
		}

		HandshakeService service = request.getService();

		if (service == null) {
			fail(out, INVALID_SERVICE);
			return;
		}

		out.writeBoolean(true);
		service.handle(context);
	}

	private void fail(ByteBuf buffer, String reason) {
		buffer.writeBoolean(false);
		ByteBufUtilities.writeString(buffer, reason);
	}

}

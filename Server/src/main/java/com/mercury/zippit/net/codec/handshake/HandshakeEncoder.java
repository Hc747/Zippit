package com.mercury.zippit.net.codec.handshake;

import com.mercury.zippit.configuration.Version;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import utilities.ByteBufUtilities;

import java.util.logging.Logger;

import static com.mercury.zippit.net.codec.handshake.HandshakeConstants.*;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
public class HandshakeEncoder extends MessageToByteEncoder<HandshakeRequest> {

	private static final Logger logger = Logger.getLogger(HandshakeEncoder.class.getSimpleName());

	private final Version version;

	public HandshakeEncoder(Version version) {
		super(HandshakeRequest.class);
		this.version = version;
	}

	@Override
	protected void encode(ChannelHandlerContext context, HandshakeRequest request, ByteBuf out) {
		logger.info(String.format("received request: %s", request));

		if (!version.equals(request.getVersion())) {
			fail(out, OUTDATED);
			return;
		}

		HandshakeService service = request.getService();

		if (service == null) {
			fail(out, INVALID_SERVICE);
			return;
		}

		out.writeByte(SUCCESS);
		service.handle(context);
	}

	private void fail(ByteBuf buffer, String reason) {
		logger.info(String.format("failed: %s", reason)); //TODO: remove
		buffer.writeByte(FAILURE);
		ByteBufUtilities.writeString(buffer, reason);
	}

}

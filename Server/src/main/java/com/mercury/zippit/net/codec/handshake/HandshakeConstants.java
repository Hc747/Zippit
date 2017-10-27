package com.mercury.zippit.net.codec.handshake;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
final class HandshakeConstants {

	private HandshakeConstants() {}

	static final int BLOCK_LENGTH = 5;

	static final String OUTDATED = "Your client is outdated. Please ensure you have the latest version.";

	static final String INVALID_SERVICE = "Invalid service requested. Could not complete your request.";

}

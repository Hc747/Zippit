package com.mercury.zippit.net.codec.handshake;

import com.mercury.zippit.net.codec.Service;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 25/10/17
 */
public final class HandshakeMessage {

	private final Service service;

	public HandshakeMessage(Service service) {
		this.service = service;
	}

	public Service getService() {
		return service;
	}

}

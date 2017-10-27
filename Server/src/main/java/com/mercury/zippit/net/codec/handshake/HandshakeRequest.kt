package com.mercury.zippit.net.codec.handshake

import com.mercury.zippit.configuration.Version

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
data class HandshakeRequest(val version: Version, val service: HandshakeService?)
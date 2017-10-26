package com.mercury.zippit.net.codec.handshake

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
data class HandshakeRequest(val versionMajor: Int, val versionMinor: Int, val serviceId: Int)
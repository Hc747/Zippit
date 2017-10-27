package com.mercury.zippit.net.codec.login

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
internal data class LoginRequest(val username: String, val password: String, val reconnecting: Boolean)
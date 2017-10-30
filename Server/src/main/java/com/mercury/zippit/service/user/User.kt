package com.mercury.zippit.service.user

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */
data class Credentials(val username: String, val password: String)

data class User(val credentials: Credentials)
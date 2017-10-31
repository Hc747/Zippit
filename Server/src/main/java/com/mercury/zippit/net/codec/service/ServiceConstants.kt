package com.mercury.zippit.net.codec.service

import java.util.concurrent.TimeUnit

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
internal object ServiceConstants {

    val HEADER_BLOCK_LENGTH = 13

    private val REQUEST_VALIDITY_DURATION_SECONDS = 30L

    val REQUEST_VALIDITY_DURATION = TimeUnit.SECONDS.toMillis(REQUEST_VALIDITY_DURATION_SECONDS)

    val FAILURE = -1

    val TIMEOUT = "Please check your connection: your latency exceeds $REQUEST_VALIDITY_DURATION_SECONDS seconds."

    val OUTDATED = "Your client is outdated. Please ensure you have the latest version."

    val INVALID_SERVICE = "Invalid service requested. Could not complete your request."

}

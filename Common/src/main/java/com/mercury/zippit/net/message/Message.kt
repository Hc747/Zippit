package com.mercury.zippit.net.message

import io.netty.buffer.ByteBuf

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */
interface OutboundMessage {

    fun encode(): ByteBuf

}
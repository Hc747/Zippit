package com.mercury.zippit.net.codec.handshake

import com.mercury.zippit.net.codec.login.LoginDecoder
import com.mercury.zippit.net.codec.login.LoginEncoder
import com.mercury.zippit.net.codec.registration.RegistrationDecoder
import com.mercury.zippit.net.codec.registration.RegistrationEncoder
import io.netty.channel.ChannelHandlerContext

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
enum class HandshakeRequestEndpoint {

    LOGIN {
        override fun handle(context: ChannelHandlerContext) {
            val pipeline = context.pipeline()
            pipeline.replace(HandshakeDecoder::class.java.simpleName, LoginDecoder::class.java.simpleName, LoginDecoder())
            pipeline.replace(HandshakeEncoder::class.java.simpleName, LoginEncoder::class.java.simpleName, LoginEncoder())
        }
    },
    REGISTRATION {
        override fun handle(context: ChannelHandlerContext) {
            val pipeline = context.pipeline()
            pipeline.replace(HandshakeDecoder::class.java.simpleName, RegistrationDecoder::class.java.simpleName, RegistrationDecoder())
            pipeline.replace(HandshakeEncoder::class.java.simpleName, RegistrationEncoder::class.java.simpleName, RegistrationEncoder())
        }
    };

    internal abstract fun handle(context: ChannelHandlerContext)

    companion object {

        private val ENDPOINTS: Map<Int, HandshakeRequestEndpoint>

        internal fun lookup(id: Int): HandshakeRequestEndpoint? {
            return ENDPOINTS[id]
        }

        init {
            val values = HashMap<Int, HandshakeRequestEndpoint>()

            for (service in HandshakeRequestEndpoint.values())
                values.put(service.ordinal, service)

            ENDPOINTS = values.toMap()
        }
    }

}

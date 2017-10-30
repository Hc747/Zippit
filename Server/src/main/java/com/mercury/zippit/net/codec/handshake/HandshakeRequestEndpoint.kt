package com.mercury.zippit.net.codec.handshake

import com.mercury.zippit.net.codec.login.LoginRequestDecoder
import com.mercury.zippit.net.codec.login.LoginResponseEncoder
import com.mercury.zippit.net.codec.registration.RegistrationRequestDecoder
import com.mercury.zippit.net.codec.registration.RegistrationResponseEncoder
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
            pipeline.replace(HandshakeRequestDecoder::class.java, LoginRequestDecoder::class.java.simpleName, LoginRequestDecoder())
            pipeline.replace(HandshakeResponseEncoder::class.java, LoginResponseEncoder::class.java.simpleName, LoginResponseEncoder())
        }
    },
    REGISTRATION {
        override fun handle(context: ChannelHandlerContext) {
            val pipeline = context.pipeline()
            pipeline.replace(HandshakeRequestDecoder::class.java, RegistrationRequestDecoder::class.java.simpleName, RegistrationRequestDecoder())
            pipeline.replace(HandshakeResponseEncoder::class.java, RegistrationResponseEncoder::class.java.simpleName, RegistrationResponseEncoder())
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

package com.mercury.zippit.net.codec.handshake

import com.mercury.zippit.net.codec.login.LoginRequestDecoder
import com.mercury.zippit.net.codec.login.LoginResponseEncoder
import com.mercury.zippit.net.codec.registration.RegistrationRequestDecoder
import com.mercury.zippit.net.codec.registration.RegistrationResponseEncoder
import com.mercury.zippit.persistence.sql.Database
import io.netty.channel.ChannelHandlerContext

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
enum class HandshakeRequestEndpoint {

    LOGIN {
        override fun handle(context: ChannelHandlerContext, database: Database) {
            val pipeline = context.pipeline()
            pipeline.replace(HandshakeRequestDecoder::class.java.simpleName, LoginRequestDecoder::class.java.simpleName, LoginRequestDecoder())
            pipeline.replace(HandshakeResponseEncoder::class.java.simpleName, LoginResponseEncoder::class.java.simpleName, LoginResponseEncoder(database))
        }
    },
    REGISTRATION {
        override fun handle(context: ChannelHandlerContext, database: Database) {
            val pipeline = context.pipeline()
            pipeline.replace(HandshakeRequestDecoder::class.java.simpleName, RegistrationRequestDecoder::class.java.simpleName, RegistrationRequestDecoder())
            pipeline.replace(HandshakeResponseEncoder::class.java.simpleName, RegistrationResponseEncoder::class.java.simpleName, RegistrationResponseEncoder(database))
        }
    };

    internal abstract fun handle(context: ChannelHandlerContext, database: Database)

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

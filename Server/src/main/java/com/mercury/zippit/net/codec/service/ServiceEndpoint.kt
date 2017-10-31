package com.mercury.zippit.net.codec.service

import com.mercury.zippit.persistence.sql.Database
import io.netty.channel.ChannelHandlerContext

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
enum class ServiceEndpoint {

    LOGIN {
        override fun handle(context: ChannelHandlerContext, database: Database) {
            val pipeline = context.pipeline()
            //pipeline.replace(HandshakeRequestDecoder::class.java.simpleName, LoginRequestDecoder::class.java.simpleName, LoginRequestDecoder())
            //pipeline.replace(OutboundMessageEncoder::class.java.simpleName, LoginResponseEncoder::class.java.simpleName, LoginResponseEncoder(database))
        }
    },
    REGISTRATION {
        override fun handle(context: ChannelHandlerContext, database: Database) {
            val pipeline = context.pipeline()
            //pipeline.replace(HandshakeRequestDecoder::class.java.simpleName, RegistrationRequestDecoder::class.java.simpleName, RegistrationRequestDecoder())
            //pipeline.replace(OutboundMessageEncoder::class.java.simpleName, RegistrationResponseEncoder::class.java.simpleName, RegistrationResponseEncoder(database))
        }
    };

    internal abstract fun handle(context: ChannelHandlerContext, database: Database)

    companion object {

        private val ENDPOINTS: Map<Int, ServiceEndpoint>

        internal fun lookup(id: Int): ServiceEndpoint? {
            return ENDPOINTS[id]
        }

        init {
            val values = HashMap<Int, ServiceEndpoint>()

            for (service in ServiceEndpoint.values())
                values.put(service.ordinal, service)

            ENDPOINTS = values.toMap()
        }
    }

}

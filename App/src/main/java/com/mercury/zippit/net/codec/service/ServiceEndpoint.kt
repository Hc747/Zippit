package com.mercury.zippit.net.codec.service

import io.netty.channel.ChannelHandlerContext

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
enum class ServiceEndpoint {

    LOGIN {
        override fun handle(context: ChannelHandlerContext) {
            val pipeline = context.pipeline()
            //pipeline.replace(ServiceDecoder::class.java.simpleName, LoginDecoder::class.java.simpleName, LoginDecoder())
            //pipeline.replace(ServiceEncoder::class.java.simpleName, LoginEncoder::class.java.simpleName, LoginEncoder())
        }
    },
    REGISTRATION {
        override fun handle(context: ChannelHandlerContext) {
            val pipeline = context.pipeline()
            //pipeline.replace(ServiceDecoder::class.java.simpleName, RegistrationDecoder::class.java.simpleName, RegistrationDecoder())
            //pipeline.replace(ServiceEncoder::class.java.simpleName, RegistrationEncoder::class.java.simpleName, RegistrationEncoder())
        }
    };

    internal abstract fun handle(context: ChannelHandlerContext)

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

package com.mercury.zippit.net.codec.service

import com.mercury.zippit.configuration.Version
import com.mercury.zippit.extensions.readString
import com.mercury.zippit.extensions.writeString
import com.mercury.zippit.net.codec.service.ServiceConstants.FAILURE
import com.mercury.zippit.net.codec.service.ServiceConstants.INVALID_SERVICE
import com.mercury.zippit.net.codec.service.ServiceConstants.INVALID_USERNAME_OR_PASSWORD
import com.mercury.zippit.net.codec.service.ServiceConstants.OUTDATED
import com.mercury.zippit.net.codec.service.ServiceConstants.PASSWORD_LENGTH_CONSTRAINT
import com.mercury.zippit.net.codec.service.ServiceConstants.REQUEST_VALIDITY_DURATION
import com.mercury.zippit.net.codec.service.ServiceConstants.TIMEOUT
import com.mercury.zippit.net.codec.service.ServiceConstants.USERNAME_LENGTH_CONSTRAINT
import com.mercury.zippit.service.user.Credentials
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import java.util.logging.Logger

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */
data class ServiceRequest(val metadata: TransactionMetadata, val version: Version, val endpoint: ServiceEndpoint, val credentials: Credentials) {
    data class TransactionMetadata(val sent: Long, val received: Long, val validated: Long, var processed: Long = 0L)
}

class ServiceRequestDecoder(private val version: Version) : MessageToMessageDecoder<ByteBuf>() {

    companion object {

        val logger = Logger.getLogger(ServiceRequestDecoder::class.java.simpleName)

    }

    override fun decode(context: ChannelHandlerContext, buffer: ByteBuf, out: MutableList<Any>) {
        val sent = buffer.readLong()
        val received = System.currentTimeMillis()
        val latency = received - sent

        if (latency < 0 || latency > REQUEST_VALIDITY_DURATION) {
            fail(context, TIMEOUT)
            return
        }

        val version = Version(buffer.readShort().toInt(), buffer.readShort().toInt())

        if (this.version != version) {
            fail(context, OUTDATED)
            return
        }

        val endpoint = ServiceEndpoint.lookup(buffer.readUnsignedByte().toInt())

        if (endpoint == null) {
            fail(context, INVALID_SERVICE)
            return
        }

        val username = buffer.readString()
        val password = buffer.readString()

        if (!USERNAME_LENGTH_CONSTRAINT.validate(username) || !PASSWORD_LENGTH_CONSTRAINT.validate(password)) {
            fail(context, INVALID_USERNAME_OR_PASSWORD)
            return
        }

        val credentials = Credentials(username, password)
        val metadata = ServiceRequest.TransactionMetadata(sent, received, validated = System.currentTimeMillis())

        val request = ServiceRequest(metadata, version, endpoint, credentials)

        logger.info(request.toString())

        out.add(request) //pass to the app handler
    }

    private fun fail(context: ChannelHandlerContext, reason: String) {
        //TODO: close channel where necessary
        logger.info(reason)
        context.writeAndFlush(context.alloc().buffer().writeByte(FAILURE).writeString(reason))
    }

}
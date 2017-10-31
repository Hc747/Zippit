package com.mercury.zippit.net

import com.mercury.zippit.net.codec.service.ServiceEndpoint
import com.mercury.zippit.net.codec.service.ServiceRequest
import com.mercury.zippit.persistence.sql.Database
import com.mercury.zippit.persistence.sql.getPreparedStatement
import com.mercury.zippit.persistence.sql.getResults
import com.mercury.zippit.service.user.User
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.util.ReferenceCountUtil
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withTimeout
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 24/10/17
 */
@Sharable
class ZippitHandler(private val database: Database) : ChannelInboundHandlerAdapter() {

    //private static final AttributeKey<Session> SESSION_KEY = AttributeKey.valueOf("session");

    override fun channelRead(context: ChannelHandlerContext, message: Any) {
        try {
            //val channel = context.channel()
            logger.info(message.toString())
            //Session session = channel.attr(SESSION_KEY).get();

            if (message is ServiceRequest) {

                when (message.endpoint) {

                    ServiceEndpoint.LOGIN -> {

                        launch {

                            try {
                                val query = "SELECT * FROM users WHERE username = ? LIMIT 1;"

                                val user = withTimeout(3, TimeUnit.SECONDS) {

                                    database.getConnection().use {

                                        it.getPreparedStatement(query).use {

                                            it.setString(1, message.credentials.username)

                                            it.getResults().use {

                                                if (!it.next()) return@withTimeout null

                                                return@withTimeout User(it.getInt("id"), it.getString("username"), it.getString("password"))

                                            }

                                        }

                                    }

                                }

                                println(user)

                            } catch (timeout: CancellationException) {

                                timeout.printStackTrace()

                            }

                        }

                    }

                    ServiceEndpoint.REGISTRATION -> {



                    }

                }

            }

            //if (session != null)
            //	session.onMessageReceived(message);
        } finally {
            ReferenceCountUtil.release(message)
        }
    }

    override fun channelActive(context: ChannelHandlerContext) {
        //val channel = context.channel()
        //channel.attr(SESSION_KEY).setIfAbsent(new LoginSession()); //TODO: create login session
        logger.info(String.format("channelActive: %s", context.channel()))
    }

    override fun channelInactive(context: ChannelHandlerContext) {
        val channel = context.channel()
        logger.info(String.format("channelInactive: %s", channel))
        //Optional.ofNullable(channel.attr(SESSION_KEY).getAndSet(null)).ifPresent(Session::destroy);//TODO
        channel.close()
    }

    override fun exceptionCaught(context: ChannelHandlerContext, cause: Throwable) {
        val channel = context.channel()
        logger.log(Level.WARNING, String.format("exceptionCaught: %s", channel), cause)
        channel.close()
    }

    companion object {

        private val logger = Logger.getLogger(ZippitHandler::class.java.simpleName)
    }

}

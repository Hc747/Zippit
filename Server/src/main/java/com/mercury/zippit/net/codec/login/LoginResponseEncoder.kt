package com.mercury.zippit.net.codec.login

import com.mercury.zippit.extensions.writeString
import com.mercury.zippit.persistence.sql.Database
import com.mercury.zippit.service.user.User
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withTimeout
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 25/10/17
 */
class LoginResponseEncoder(private val database: Database) : MessageToByteEncoder<LoginRequest>(LoginRequest::class.java) {

    companion object {

        private val logger = Logger.getLogger(LoginResponseEncoder::class.java.simpleName)

    }

    override fun encode(context: ChannelHandlerContext, request: LoginRequest, response: ByteBuf) {

        logger.info("Processing request: $request at: ${System.currentTimeMillis()}")

        //TODO:


        launch {

            try {

                val user = withTimeout(5, TimeUnit.SECONDS) {
                    val query = "SELECT * FROM users WHERE email_address = '${request.username}' LIMIT 1;"

                    database.executeAndTransform(query, {
                        if (!it.next()) null
                        else User(it.getInt("id"), it.getString("email_address"), it.getString("password"))
                    })
                }

                val buffer = Unpooled.directBuffer()

                if (user == null) buffer.writeString("The username or password supplied was incorrect.")
                else buffer.writeString("Welcome, $user")

                context.writeAndFlush(buffer)

            } catch (timeout: Exception) {

                context.writeAndFlush(Unpooled.directBuffer().writeString("Request timed out, please try again."))

            }

            logger.info("Finished request: $request at ${System.currentTimeMillis()}")

        }
    }



    private fun fail(buffer: ByteBuf, reason: String) {
        println("Failure: $reason")
    }

}

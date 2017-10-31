package com.mercury.zippit

import com.mercury.zippit.configuration.ZippitConfiguration
import com.mercury.zippit.net.ZippitChannelInitialiser
import com.mercury.zippit.net.ZippitHandler
import com.mercury.zippit.persistence.sql.Database
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 24/10/17
 */
class Zippit(private val configuration: ZippitConfiguration) : Runnable {

    private val bootstrap = ServerBootstrap()
    private val group = NioEventLoopGroup()
    private val database = Database(configuration.datasource!!)

    override fun run() {
        try {
            val handler = ZippitHandler()

            bootstrap.group(group)
            bootstrap.channel(NioServerSocketChannel::class.java)

            bootstrap.handler(LoggingHandler(LogLevel.INFO))//TODO
            bootstrap.childHandler(ZippitChannelInitialiser(handler, database, configuration.version))

            bootstrap.option(ChannelOption.SO_BACKLOG, 128)
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
            bootstrap.childOption(ChannelOption.TCP_NODELAY, true)

            val binding = bootstrap.bind(configuration.port).syncUninterruptibly()

            binding.channel().closeFuture().syncUninterruptibly()
        } finally {
            group.shutdownGracefully()
            database.close()
        }
    }

}

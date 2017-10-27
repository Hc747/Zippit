package com.mercury.zippit;

import com.google.common.base.Preconditions;
import com.mercury.zippit.configuration.ZippitConfiguration;
import com.mercury.zippit.net.ZippitChannelInitialiser;
import com.mercury.zippit.net.ZippitHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.logging.Logger;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 24/10/17
 */
public class Zippit {

	private static final Logger logger = Logger.getLogger(Zippit.class.getSimpleName());

	private final ServerBootstrap bootstrap = new ServerBootstrap();
	private final EventLoopGroup group = new NioEventLoopGroup();

	private final ZippitConfiguration configuration;

	public Zippit(ZippitConfiguration configuration) {
		this.configuration = Preconditions.checkNotNull(configuration, "configuration");
	}

	public void run() {
		try {
			bootstrap.group(group);
			bootstrap.channel(NioServerSocketChannel.class);

			bootstrap.handler(new LoggingHandler(LogLevel.INFO));//TODO
			bootstrap.childHandler(new ZippitChannelInitialiser(new ZippitHandler(), configuration.getVersion()));

			bootstrap.option(ChannelOption.SO_BACKLOG, 128);
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.childOption(ChannelOption.TCP_NODELAY, true);

			ChannelFuture binding = bootstrap.bind(configuration.getPort()).syncUninterruptibly();

			binding.channel().closeFuture().syncUninterruptibly();
		} finally {
			group.shutdownGracefully();
		}
	}

}

package com.mercury.zippit.net;

import com.mercury.zippit.net.codec.login.LoginDecoder;
import com.mercury.zippit.net.codec.login.LoginEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.Objects;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
public final class ZippitChannelInitialiser extends ChannelInitializer<SocketChannel> {

	private final ZippitHandler handler;

	public ZippitChannelInitialiser(ZippitHandler handler) {
		this.handler = Objects.requireNonNull(handler, "handler");
	}

	@Override
	protected void initChannel(SocketChannel ch) {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(LoginDecoder.class.getSimpleName(), new LoginDecoder());
		pipeline.addLast(LoginEncoder.class.getSimpleName(), new LoginEncoder());
		pipeline.addLast(ZippitHandler.class.getSimpleName(), handler);
	}
}

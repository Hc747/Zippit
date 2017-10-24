package com.mercury.zippit.net;

import com.mercury.zippit.net.codec.login.LoginDecoder;
import com.mercury.zippit.net.codec.login.LoginEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 24/10/17
 */
public final class ZippitChannelInitialiser extends ChannelInitializer<SocketChannel> {

	private final ZippitHandler handler;

	public ZippitChannelInitialiser(ZippitHandler handler) {
		this.handler = handler;
	}

	@Override
	protected void initChannel(SocketChannel ch) {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addFirst(LoginEncoder.class.getSimpleName(), new LoginEncoder());
		pipeline.addAfter(LoginEncoder.class.getSimpleName(), LoginDecoder.class.getSimpleName(), new LoginDecoder());
		pipeline.addLast(IdleStateHandler.class.getSimpleName(), new IdleStateHandler(NetworkConstants.IDLE_TIME, 0, 0));
		pipeline.addLast(ZippitHandler.class.getSimpleName(), handler);
	}

}

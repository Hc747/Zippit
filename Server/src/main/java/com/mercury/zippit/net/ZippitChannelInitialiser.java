package com.mercury.zippit.net;

import com.mercury.zippit.configuration.Version;
import com.mercury.zippit.net.codec.handshake.HandshakeRequestDecoder;
import com.mercury.zippit.net.codec.handshake.HandshakeResponseEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.Objects;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 24/10/17
 */
public final class ZippitChannelInitialiser extends ChannelInitializer<SocketChannel> {

	private final ZippitHandler handler;
	private final Version version;

	public ZippitChannelInitialiser(ZippitHandler handler, Version version) {
		this.handler = Objects.requireNonNull(handler, "handler");
		this.version = Objects.requireNonNull(version, "version");
	}

	@Override
	protected void initChannel(SocketChannel ch) {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(HandshakeRequestDecoder.class.getSimpleName(), new HandshakeRequestDecoder());
		pipeline.addLast(HandshakeResponseEncoder.class.getSimpleName(), new HandshakeResponseEncoder(version));
		pipeline.addLast(IdleStateHandler.class.getSimpleName(), new IdleStateHandler(NetworkConstants.IDLE_TIME, 0, 0));

		//TODO: consider using a seperate thread pool for the "handler"
		//if the business logic is not async or takes a long time to compute
		pipeline.addLast(ZippitHandler.class.getSimpleName(), handler);
	}

}

package com.mercury.zippit.net.codec.handshake;

import com.google.common.collect.ImmutableMap;
import com.mercury.zippit.net.codec.login.LoginDecoder;
import com.mercury.zippit.net.codec.login.LoginEncoder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import kotlin.NotImplementedError;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
public enum HandshakeService {

	LOGIN {
		@Override
		void handle(ChannelHandlerContext context) {
			ChannelPipeline pipeline = context.pipeline();
			pipeline.replace(HandshakeDecoder.class, LoginDecoder.class.getSimpleName(), new LoginDecoder());
			pipeline.replace(HandshakeEncoder.class, LoginEncoder.class.getSimpleName(), new LoginEncoder());
		}
	},
	REGISTRATION {
		@Override
		void handle(ChannelHandlerContext context) {
			throw new NotImplementedError("TODO: implement registration handler");
			//TODO
		}
	};

	abstract void handle(ChannelHandlerContext context);

	private static final ImmutableMap<Integer, HandshakeService> services;

	public static HandshakeService lookup(int id) {
		return services.get(id);
	}

	static {
		Map<Integer, HandshakeService> values = new HashMap<>();

		for (HandshakeService service : HandshakeService.values())
			values.put(service.ordinal(), service);

		services = ImmutableMap.copyOf(values);
	}

}

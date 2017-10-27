package com.mercury.zippit.net.codec.handshake;

import com.google.common.collect.ImmutableMap;
import com.mercury.zippit.net.codec.login.LoginDecoder;
import com.mercury.zippit.net.codec.login.LoginEncoder;
import com.mercury.zippit.net.codec.registration.RegistrationDecoder;
import com.mercury.zippit.net.codec.registration.RegistrationEncoder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
enum HandshakeRequestEndpoint {

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
			ChannelPipeline pipeline = context.pipeline();
			pipeline.replace(HandshakeDecoder.class, RegistrationDecoder.class.getSimpleName(), new RegistrationDecoder());
			pipeline.replace(HandshakeEncoder.class, RegistrationEncoder.class.getSimpleName(), new RegistrationEncoder());
		}
	};

	abstract void handle(ChannelHandlerContext context);

	private static final ImmutableMap<Integer, HandshakeRequestEndpoint> ENDPOINTS;

	static HandshakeRequestEndpoint lookup(int id) {
		return ENDPOINTS.get(id);
	}

	static {
		Map<Integer, HandshakeRequestEndpoint> values = new HashMap<>();

		for (HandshakeRequestEndpoint service : HandshakeRequestEndpoint.values())
			values.put(service.ordinal(), service);

		ENDPOINTS = ImmutableMap.copyOf(values);
	}

}

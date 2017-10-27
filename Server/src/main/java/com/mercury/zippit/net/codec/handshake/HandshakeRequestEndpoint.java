package com.mercury.zippit.net.codec.handshake;

import com.google.common.collect.ImmutableMap;
import com.mercury.zippit.net.codec.login.LoginRequestDecoder;
import com.mercury.zippit.net.codec.login.LoginResponseEncoder;
import com.mercury.zippit.net.codec.registration.RegistrationRequestDecoder;
import com.mercury.zippit.net.codec.registration.RegistrationResponseEncoder;
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
			pipeline.replace(HandshakeRequestDecoder.class, LoginRequestDecoder.class.getSimpleName(), new LoginRequestDecoder());
			pipeline.replace(HandshakeResponseEncoder.class, LoginResponseEncoder.class.getSimpleName(), new LoginResponseEncoder());
		}
	},
	REGISTRATION {
		@Override
		void handle(ChannelHandlerContext context) {
			ChannelPipeline pipeline = context.pipeline();
			pipeline.replace(HandshakeRequestDecoder.class, RegistrationRequestDecoder.class.getSimpleName(), new RegistrationRequestDecoder());
			pipeline.replace(HandshakeResponseEncoder.class, RegistrationResponseEncoder.class.getSimpleName(), new RegistrationResponseEncoder());
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

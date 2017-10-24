package com.mercury.zippit.net;

import com.mercury.zippit.net.codec.handshake.HandshakeMessage;
import com.mercury.zippit.service.user.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.netty.channel.ChannelHandler.Sharable;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 24/10/17
 */
@Sharable
public final class ZippitHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger.getLogger(ZippitHandler.class.getSimpleName());

	private static final AttributeKey<Session> SESSION_KEY = AttributeKey.valueOf("session");

	@Override
	public void channelInactive(ChannelHandlerContext context) {
		Channel channel = context.channel();
		Optional.ofNullable(channel.attr(SESSION_KEY).getAndSet(null)).ifPresent(Session::destroy);
		logger.info(String.format("channelInactive: %s", channel));
		channel.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
		try {
			Channel channel = context.channel();
			Attribute<Session> attribute = channel.attr(SESSION_KEY);
			Session session = attribute.get();

			if (session != null) {
				session.onMessageReceived(message);
				return;
			}

			if (message instanceof HandshakeMessage) {

				HandshakeMessage handshake = (HandshakeMessage) message;

				logger.info(String.format("set session of type: %s", handshake.getService()));

				//TODO

				switch (handshake.getService()) {
					case APP:
						break;

					case UPDATE:
						break;
				}
			}


		} finally {
			ReferenceCountUtil.release(message);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		Channel channel = context.channel();
		logger.log(Level.WARNING, String.format("exceptionCaught: %s", channel), cause);
		channel.close();
	}

}

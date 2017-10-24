package com.mercury.zippit.net;

import com.mercury.zippit.service.user.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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
	public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
		try {
			Channel channel = context.channel();
			logger.info(String.format("channelRead: %s", channel));
			Session session = channel.attr(SESSION_KEY).get();

			if (session != null)
				session.onMessageReceived(message);

		} finally {
			ReferenceCountUtil.release(message);
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext context) {
		Channel channel = context.channel();
		//channel.attr(SESSION_KEY).setIfAbsent(new LoginSession()); //TODO: create login session
		logger.info(String.format("channelActive: %s", channel));
	}

	@Override
	public void channelInactive(ChannelHandlerContext context) {
		Channel channel = context.channel();
		logger.info(String.format("channelInactive: %s", channel));
		Optional.ofNullable(channel.attr(SESSION_KEY).getAndSet(null)).ifPresent(Session::destroy);
		channel.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		Channel channel = context.channel();
		logger.log(Level.WARNING, String.format("exceptionCaught: %s", channel), cause);
		channel.close();
	}

}

package com.mercury.zippit.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.logging.Logger;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
public final class ZippitHandler extends ChannelInboundHandlerAdapter {

	//private static final AttributeKey<Session> SESSION_KEY = AttributeKey.valueOf("session");

	//TODO: create session
	private static final Logger logger = Logger.getLogger(ZippitHandler.class.getSimpleName());

	@Override
	public void channelRead(ChannelHandlerContext context, Object message) {
		try {
			//Channel channel = context.channel();
			//Session session = channel.attr(SESSION_KEY).get();

			//if (session != null)
			//	session.onMessageReceived(message);
			logger.info(message.toString());

		} finally {
			ReferenceCountUtil.release(message);
		}
	}

	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		cause.printStackTrace();
		context.close();
	}

}

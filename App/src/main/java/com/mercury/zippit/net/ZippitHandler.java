package com.mercury.zippit.net;

import com.mercury.zippit.net.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
public final class ZippitHandler extends ChannelInboundHandlerAdapter {

	private static final AttributeKey<Session> SESSION_KEY = AttributeKey.valueOf("session");

	//TODO: create session

	@Override
	public void channelRead(ChannelHandlerContext context, Object message) {
		try {
			Channel channel = context.channel();
			Session session = channel.attr(SESSION_KEY).get();

			if (session != null)
				session.onMessageReceived(message);

		} finally {
			ReferenceCountUtil.release(message);
		}
	}

	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		cause.printStackTrace();
		context.close();
	}

}

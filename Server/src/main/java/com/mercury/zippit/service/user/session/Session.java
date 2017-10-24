package com.mercury.zippit.service.user.session;

import io.netty.channel.Channel;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 25/10/17
 */
public abstract class Session {

	protected final Channel channel;

	public Session(Channel channel) {
		this.channel = channel;
	}

	public abstract void destroy();

	public abstract void onMessageReceived(Object message) throws Exception;

}

package com.mercury.zippit.net.session;

import io.netty.channel.Channel;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
public abstract class Session {

	protected final Channel channel;

	protected Session(Channel channel) {
		this.channel = channel;
	}

	public abstract void destroy();

	public abstract void onMessageReceived(Object message);

}

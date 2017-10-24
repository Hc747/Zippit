package com.mercury.zippit.configuration;

import com.mercury.zippit.builder.Builder;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 24/10/17
 */
public final class ZippitConfiguration {

	private final int port;

	private ZippitConfiguration(int port) {
		this.port = port;
	}

	public static ZippitConfigurationBuilder builder() {
		return new ZippitConfigurationBuilder();
	}

	public int getPort() {
		return port;
	}

	public static class ZippitConfigurationBuilder implements Builder<ZippitConfiguration> {

		private int port = ZippitConstants.DEFAULT_PORT;

		public ZippitConfigurationBuilder port(int port) {
			this.port = port;
			return this;
		}

		@Override
		public ZippitConfiguration build() {
			return new ZippitConfiguration(port);
		}

	}

}

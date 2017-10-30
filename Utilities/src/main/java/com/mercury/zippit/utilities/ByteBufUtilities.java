package com.mercury.zippit.utilities;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 26/10/17
 */
public final class ByteBufUtilities {

	private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	private ByteBufUtilities() {}

	public static ByteBuf writeString(ByteBuf buffer, String message) {
		byte[] data = message.getBytes(DEFAULT_CHARSET);
		return buffer.writeShort(data.length).writeBytes(data);
	}

	//TODO: length & sanitisation

	public static String readString(ByteBuf buffer) {
		int length = buffer.readShort();
		if (!buffer.isReadable(length))
			throw new IllegalStateException(String.format("expected length: %d, actual length: %d", length, buffer.readableBytes()));
		byte[] data = new byte[length];
		buffer.readBytes(data);
		return new String(data, DEFAULT_CHARSET);
	}

}

package com.mercury.zippit.net.codec;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 25/10/17
 */
public enum Service {

	APP,
	UPDATE
	;

	private static final ImmutableMap<Integer, Service> SERVICES;

	public static Service get(int id) {
		return SERVICES.get(id);
	}

	static {
		Map<Integer, Service> services = new HashMap<>();

		for (Service service : Service.values())
			services.put(service.ordinal(), service);

		SERVICES = ImmutableMap.copyOf(services);
	}

}

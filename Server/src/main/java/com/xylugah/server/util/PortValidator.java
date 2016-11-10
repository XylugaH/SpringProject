package com.xylugah.server.util;

public class PortValidator {
	public static final int minValue = 1024;
	public static final int maxValue = 49151;

	public static boolean validPortValue(final int port) {
		if (port < minValue || port > maxValue) {
			return true;
		} else {
			return false;
		}
	}
}

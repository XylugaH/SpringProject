package com.xylugah.server.test;

import org.junit.Assert;
import org.junit.Test;

import com.xylugah.server.util.PortValidator;

public class PortValidatorTest {

	private final int[] validPort = { 1024, 49151, 8080, 1251, 2048 };
	private final int[] invalidPorst = { 1023, 49152, 0, -1024, Integer.MAX_VALUE, Integer.MIN_VALUE, };

	@Test()
	public void ValidPortTest() {
		for (int i = 0; i < validPort.length; i++) {
			int port = validPort[i];
			boolean valid = PortValidator.validPortValue(port);
			System.out.println("Port is valid : " + port + " , " + valid);
			Assert.assertTrue(valid);
		}
	}

	@Test()
	public void InValidPortTest() {
		for (int i = 0; i < invalidPorst.length; i++) {
			int port = invalidPorst[i];
			boolean valid = PortValidator.validPortValue(port);
			System.out.println("Port is valid : " + port + " , " + valid);
			Assert.assertFalse(valid);
		}
	}

}

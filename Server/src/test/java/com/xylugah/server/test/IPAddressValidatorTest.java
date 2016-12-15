package com.xylugah.server.test;

import org.junit.Assert;
import org.junit.Test;

import com.xylugah.server.util.IPAddressValidator;

public class IPAddressValidatorTest {

	private IPAddressValidator ipAddressValidator = new IPAddressValidator();

	private final String[] validIPAdress = { "1.1.1.1", "255.255.255.255", "192.168.1.1", "10.10.1.1", "132.254.111.10",
			"26.10.2.10", "127.0.0.1" };
	private final String[] invalidIPAdress = { "10.10.10", "10.10", "10", "a.a.a.a", "10.0.0.a", "10.10.10.256",
			"222.222.2.999", "999.10.10.20", "2222.22.22.22", "22.2222.22.2", "10.10.10", "10.10.10", "10.10.-1.1",
			"" };

	@Test()
	public void ValidIPAddressTest() {
		for (int i = 0; i < validIPAdress.length; i++) {
			String ip = validIPAdress[i];
			boolean valid = ipAddressValidator.validate(ip);
			System.out.println("IPAddress is valid : " + ip + " , " + valid);
			Assert.assertTrue(valid);
		}
	}

	@Test()
	public void InValidIPAddressTest() {
		for (int i = 0; i < invalidIPAdress.length; i++) {
			String ip = invalidIPAdress[i];
			boolean valid = ipAddressValidator.validate(ip);
			System.out.println("IPAddress is valid : " + ip + " , " + valid);
			Assert.assertFalse(valid);
		}
	}

	@Test()
	public void NullIPAddressTest() {
		boolean valid = ipAddressValidator.validate(null);
		Assert.assertFalse(valid);
	}

}

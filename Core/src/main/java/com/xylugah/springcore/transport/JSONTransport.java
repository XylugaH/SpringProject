package com.xylugah.springcore.transport;

import java.net.Socket;

public class JSONTransport implements Transport {

	@Override
	public Object receive(final Socket socket) {
		System.out.println("JSONTransport receive");
		return null;

	}

	@Override
	public void transmit(final Object response, final Socket socket) {
		System.out.println("JSONTransport transmit");

	}

}

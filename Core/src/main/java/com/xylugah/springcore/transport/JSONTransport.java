package com.xylugah.springcore.transport;

public class JSONTransport implements Transport {

	@Override
	public void receive() {
		System.out.println("JSONTransport receive");

	}

	@Override
	public void transmit() {
		System.out.println("JSONTransport transmit");

	}

}

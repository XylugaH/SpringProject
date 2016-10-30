package com.xylugah.springcore.transport;

public class SerializationTransport implements Transport {

	@Override
	public void receive() {
		System.out.println("SerializationTransport receive");

	}

	@Override
	public void transmit() {
		System.out.println("SerializationTransport transmit");

	}

}

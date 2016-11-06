package com.xylugah.springcore.transport;

import java.net.Socket;

import com.xylugah.springcore.model.Request;
import com.xylugah.springcore.model.Response;

public class JSONTransport implements Transport {

	@Override
	public Request receive(final Socket socket) {
		System.out.println("JSONTransport receive");
		return null;

	}

	@Override
	public void transmit(final Response response, final Socket socket) {
		System.out.println("JSONTransport transmit");

	}

}

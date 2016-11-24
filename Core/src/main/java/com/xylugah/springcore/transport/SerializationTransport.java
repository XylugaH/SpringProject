package com.xylugah.springcore.transport;

import java.io.IOException;
import java.net.Socket;

import com.xylugah.springcore.model.Request;
import com.xylugah.springcore.model.Response;

public class SerializationTransport implements Transport {

	@Override
	public Request receiveRequest(final Socket socket) {
		Object obj = null;
		try {
			obj = receive(socket);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (obj instanceof Request) {
			return (Request) obj;
		} else {
			return null;
		}
	}

	@Override
	public void transmitRequest(final Request request, final Socket socket) {
		try {
			transmit(request, socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Response receiveResponse(final Socket socket) {
		Object obj = null;
		try {
			obj = receive(socket);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (obj instanceof Response) {
			return (Response) obj;
		} else {
			return null;
		}
	}

	@Override
	public void transmitResponse(final Response response, final Socket socket) {
		try {
			transmit(response, socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

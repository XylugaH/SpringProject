package com.xylugah.springcore.transport;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.xylugah.springcore.model.Request;
import com.xylugah.springcore.model.Response;

public class SerializationTransport implements Transport {

	@Override
	public Request receive(final Socket socket) {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			Object request = inputStream.readObject();
			if (request instanceof Request) {
				return (Request) request;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void transmit(final Response response, final Socket socket) {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(response);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

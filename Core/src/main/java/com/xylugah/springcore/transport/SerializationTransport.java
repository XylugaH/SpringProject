package com.xylugah.springcore.transport;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.xylugah.springcore.model.Request;

public class SerializationTransport implements Transport {

	@Override
	public Object receive(final Socket socket) {
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
	public void transmit(final Object response, final Socket socket) {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(response);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

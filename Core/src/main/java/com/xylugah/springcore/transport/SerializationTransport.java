package com.xylugah.springcore.transport;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.xylugah.springcore.model.Request;
import com.xylugah.springcore.model.Response;

public class SerializationTransport implements Transport {

	@Override
	public Request receiveRequest(Socket socket) {
		Object obj = receive(socket);
		if (obj instanceof Request) {
			return (Request) obj;
		} else {
			return null;
		}
	}

	@Override
	public void transmitRequest(Request request, Socket socket) {
		transmit(request, socket);
	}

	@Override
	public Response reciveResponse(Socket socket) {
		Object obj = receive(socket);
		if (obj instanceof Response) {
			return (Response) obj;
		} else {
			return null;
		}
	}

	@Override
	public void transmitResponse(Response response, Socket socket) {
		transmit(response, socket);
	}

	public Object receive(final Socket socket) {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			Object inputObj = inputStream.readObject();
			return inputObj;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void transmit(final Object outputObj, final Socket socket) {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(outputObj);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

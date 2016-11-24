package com.xylugah.springcore.transport;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.xylugah.springcore.model.Request;
import com.xylugah.springcore.model.Response;

public interface Transport {
	public Request receiveRequest(final Socket socket);

	public void transmitRequest(final Request request, final Socket socket);

	public Response receiveResponse(final Socket socket);

	public void transmitResponse(final Response response, final Socket socket);

	default public Object receive(final Socket socket) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		Object inputObj = inputStream.readObject();
		return inputObj;
	}

	default public void transmit(final Object outputObj, final Socket socket) throws IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		outputStream.writeObject(outputObj);
		outputStream.flush();
	}
}

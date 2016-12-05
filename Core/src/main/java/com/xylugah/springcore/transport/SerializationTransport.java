package com.xylugah.springcore.transport;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.xylugah.springcore.messages.Request;
import com.xylugah.springcore.messages.Response;

public class SerializationTransport implements Transport {
	public static final Logger logger = Logger.getLogger(SerializationTransport.class);

	@Override
	public Request receiveRequest(final Socket socket) {
		Object obj = null;
		try {
			obj = receive(socket);
		} catch (ClassNotFoundException e) {
			logger.error("Class not found!", e);
			return null;
		} catch (IOException e) {
			logger.error("IO error!", e);
			return null;
		}

		if (obj instanceof Request) {
			return (Request) obj;
		} else {
			if (logger.isInfoEnabled()) {
				logger.info("Object not instance of request!");
			}
			return null;
		}
	}

	@Override
	public void transmitRequest(final Request request, final Socket socket) {
		try {
			transmit(request, socket);
		} catch (IOException e) {
			logger.error("IO error!", e);
		}
	}

	@Override
	public Response receiveResponse(final Socket socket) {
		Object obj = null;
		try {
			obj = receive(socket);
		} catch (ClassNotFoundException e) {
			logger.error("Class not found!", e);
			return null;
		} catch (IOException e) {
			logger.error("IO error!", e);
			return null;
		}
		if (obj instanceof Response) {
			return (Response) obj;
		} else {
			if (logger.isInfoEnabled()) {
				logger.info("Object not instance of response!");
			}
			return null;
		}
	}

	@Override
	public void transmitResponse(final Response response, final Socket socket) {
		try {
			transmit(response, socket);
		} catch (IOException e) {
			logger.error("IO error!", e);
		}
	}

	private Object receive(final Socket socket) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		Object inputObj = inputStream.readObject();
		return inputObj;
	}

	private void transmit(final Object outputObj, final Socket socket) throws IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		outputStream.writeObject(outputObj);
		outputStream.flush();
	}

}

package com.xylugah.client.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.xylugah.client.main.Main;
import com.xylugah.springcore.core.Request;
import com.xylugah.springcore.core.Response;
import com.xylugah.springcore.transport.Transport;

public class Controller extends Thread {
	private Socket socket;

	public Controller(final Socket socket) {
		this.socket = socket;
		this.start();
	}

	@Override
	public void run() {
		Request request = readRequest();
		System.out.println(request.getAction());
		Response response = new Response();
		writeResponse(response);
		Transport myClass = (Transport) Main.context.getBean("Transport");
		myClass.receive();
	}

	private Request readRequest() {
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

	private void writeResponse(final Response response) {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(response);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.xylugah.client.core;

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
		Transport transport = (Transport) Main.context.getBean("Transport");
		Request request = transport.receive(socket);

		System.out.println(request.getAction());
		Response response = new Response();
		transport.transmit(response, socket);
	}
}

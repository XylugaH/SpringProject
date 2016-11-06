package com.xylugah.client.core;

import java.net.Socket;

import com.xylugah.client.main.Main;
import com.xylugah.springcore.model.Request;
import com.xylugah.springcore.model.Response;
import com.xylugah.springcore.service.Service;
import com.xylugah.springcore.transport.Transport;

public class Controller extends Thread {
	private Socket socket;

	public Controller(final Socket socket) {
		this.socket = socket;
		this.start();
	}

	@Override
	public void run() {
		final Transport transport = (Transport) Main.context.getBean("Transport");
		final Request request = transport.receive(socket);
		final ServiceList serviceList = (ServiceList) Main.context.getBean("ServiceList");
		final Service service = serviceList.getService(request.getAction());
		final Response response = service.action();
		transport.transmit(response, socket);
	}
}

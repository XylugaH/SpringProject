package com.xylugah.client.core;

import java.net.Socket;

import org.apache.log4j.Logger;

import com.xylugah.client.main.Main;
import com.xylugah.springcore.model.Request;
import com.xylugah.springcore.model.Response;
import com.xylugah.springcore.service.Service;
import com.xylugah.springcore.transport.Transport;

public class Controller extends Thread {
	private static final Logger logger = Logger.getLogger(Controller.class);
	private Socket socket;

	public Controller(final Socket socket) {
		this.socket = socket;
		this.start();
	}

	@Override
	public void run() {
		Transport transport = (Transport) Main.context.getBean("Transport");
		Object requestObj = transport.receive(socket);
		if (requestObj instanceof Request) {
			Request request = (Request) requestObj;
			logger.info("Get request " + request.getAction());
			ServiceList serviceList = (ServiceList) Main.context.getBean("ServiceList");
			Service service = serviceList.getService(request.getAction());
			if (service != null) {
				Response response = service.action();
				transport.transmit(response, socket);
				logger.info("Data transfer is completed successfully!!!");
			} else {
				logger.info("Service " + request.getAction() + " not found in service list!!!");
			}
		} else {
			logger.info("Unknown request!");
		}

	}
}

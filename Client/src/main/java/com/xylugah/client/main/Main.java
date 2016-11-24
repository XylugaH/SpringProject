package com.xylugah.client.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xylugah.client.core.Controller;

public class Main {
	public static final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	public static final int port = 1238;
	public static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		// Request request = new Request(Action.GET_INVIRONMENTS);
		// mapper.writeValue(System.out, request);
		//
		// System.out.println("str");

		// String jsonInString = mapper.writeValueAsString(request);
		// System.out.println(jsonInString);
		// Request obj = mapper.readValue(jsonInString, Request.class);
		// System.out.println(obj.getAction());
		try (ServerSocket socketListener = new ServerSocket(port)) {
			while (true) {
				logger.info("Waiting connection ...");
				Socket socket = socketListener.accept();
				logger.info("Connection sucessfull !!!");
				new Controller(socket);
			}
		}

	}

}

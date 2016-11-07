package com.xylugah.client.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xylugah.client.core.Controller;

public class Main {
	public static final ApplicationContext context = new ClassPathXmlApplicationContext("Context.xml");
	public static final int port = 1238;
	public static final Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws IOException {

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

package com.xylugah.client.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xylugah.client.core.Controller;
import com.xylugah.springcore.transport.Transport;

public class Main {
	public static final ApplicationContext context = new ClassPathXmlApplicationContext("Context.xml");
	public static final int port = 1238;

	public static void main(String[] args) throws IOException {
		Transport myClass = (Transport) Main.context.getBean("Transport");
		myClass.receive();
		try (ServerSocket socketListener = new ServerSocket(port)) {
			while (true) {
				System.out.println("Waiting connection ...");
				Socket socket = socketListener.accept();
				System.out.println("Connection sucessfull !!!");
				new Controller(socket);
			}
		}

	}

}

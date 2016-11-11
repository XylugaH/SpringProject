package com.xylugah.springcore.model;

public class Client {
	private int id;
	private String ip;
	private int port;

	public Client() {
		super();
	}

	public Client(final String ip, final int port, final int id) {
		this.ip = ip;
		this.port = port;
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

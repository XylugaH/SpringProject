package com.xylugah.springcore.entity;

public class Client extends Entity {
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

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder().append("ip=").append(ip).append(" ").append("port=").append(port);
		return str.toString();
	}

}

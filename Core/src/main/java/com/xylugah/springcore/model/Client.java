package com.xylugah.springcore.model;

import java.util.List;

public class Client {
	private String ip;
	private int port;

	private long memorySize;
	private long diskSize;
	private List<String> processesList;

	public Client() {
		super();
	}

	public Client(final String ip, final int port) {
		this.ip = ip;
		this.port = port;
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

	public long getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(long memorySize) {
		this.memorySize = memorySize;
	}

	public long getDiskSize() {
		return diskSize;
	}

	public void setDiskSize(long diskSize) {
		this.diskSize = diskSize;
	}

	public List<String> getProcessesList() {
		return processesList;
	}

	public void setProcessesList(List<String> processesList) {
		this.processesList = processesList;
	}

}

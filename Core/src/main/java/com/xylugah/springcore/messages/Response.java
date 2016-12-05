package com.xylugah.springcore.messages;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
	private static final long serialVersionUID = -7754127567397444737L;
	private double freeMemory;
	private double freeDiskSpace;
	private List<String> processesList;

	public Response() {
		super();
	}

	public Response(final double freeMemory, final double freeDiskSpace, final List<String> processesList) {
		this.freeMemory = freeMemory;
		this.freeDiskSpace = freeDiskSpace;
		this.processesList = processesList;
	}

	public double getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(double freeMemory) {
		this.freeMemory = freeMemory;
	}

	public double getFreeDiskSpace() {
		return freeDiskSpace;
	}

	public void setFreeDiskSpace(double freeDiskSpace) {
		this.freeDiskSpace = freeDiskSpace;
	}

	public List<String> getProcessesList() {
		return processesList;
	}

	public void setProcessesList(List<String> processesList) {
		this.processesList = processesList;
	}

}

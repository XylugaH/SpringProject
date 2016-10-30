package com.xylugah.springcore.core;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
	private static final long serialVersionUID = -7754127567397444737L;
	private double freeMemory;
	private double freeDiskSpace;
	private List<String> processesList;

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

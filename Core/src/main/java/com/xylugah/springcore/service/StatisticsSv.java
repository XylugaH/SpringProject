package com.xylugah.springcore.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import com.sun.management.OperatingSystemMXBean;
import com.xylugah.springcore.core.Response;

public class StatisticsSv extends Service {
	private final int mb = 1024 * 1024;

	@Override
	public Response action() {
		System.out.println("statistic");
		return null;
	}

	private long getFreeRAM() {
		final long memorySize = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean())
				.getFreePhysicalMemorySize();
		return memorySize / mb;
	}

	private long getFreeDiskSpace() {
		final long diskSize = new File("/").getFreeSpace();
		return diskSize / mb;
	}

	private List<String> getProcessList() throws IOException {
		List<String> processesList = new ArrayList<>();
		String line;
		Process p = Runtime.getRuntime().exec("tasklist.exe");
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = input.readLine()) != null) {
			processesList.add(line);
		}
		input.close();
		return processesList;
	}
}

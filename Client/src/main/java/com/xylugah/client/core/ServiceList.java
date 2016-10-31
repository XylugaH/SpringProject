package com.xylugah.client.core;

import java.util.Map;

import com.xylugah.springcore.core.Action;
import com.xylugah.springcore.service.Service;

public class ServiceList {
	private Map<Action, Service> serviceList;

	public ServiceList(final Map<Action, Service> serviceList) {
		this.serviceList = serviceList;
	}

	public Map<Action, Service> getServiceList() {
		return serviceList;
	}

}

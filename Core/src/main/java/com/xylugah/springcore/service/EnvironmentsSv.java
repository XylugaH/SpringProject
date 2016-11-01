package com.xylugah.springcore.service;

import com.xylugah.springcore.core.Response;

public class EnvironmentsSv extends Service {

	@Override
	public Response action() {
		System.out.println("environment");
		return new Response();
	}

}

package com.xylugah.springcore.service;

import org.apache.log4j.Logger;

import com.xylugah.springcore.model.Response;

public class EnvironmentsSv extends Service {
	private static final Logger logger = Logger.getLogger(EnvironmentsSv.class);

	@Override
	public Response action() {
		logger.info("In EnvironmentsSv action");
		return null;
	}

}

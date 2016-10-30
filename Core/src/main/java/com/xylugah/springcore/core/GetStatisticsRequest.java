package com.xylugah.springcore.core;

public class GetStatisticsRequest extends Request {
	private final Action action = Action.GET_STATISTICS;

	@Override
	public Action getAction() {
		return action;
	}

}

package com.xylugah.springcore.core;

public class GetStatisticsRequest extends Request {
	private static final long serialVersionUID = -6088213404326495758L;
	private final Action action = Action.GET_STATISTICS;

	@Override
	public Action getAction() {
		return action;
	}

}

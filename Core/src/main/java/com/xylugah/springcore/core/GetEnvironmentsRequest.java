package com.xylugah.springcore.core;

public class GetEnvironmentsRequest extends Request {
	private final Action action = Action.GET_INVIRONMENTS;

	@Override
	public Action getAction() {
		return action;
	}

}

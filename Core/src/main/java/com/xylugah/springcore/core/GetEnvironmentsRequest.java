package com.xylugah.springcore.core;

public class GetEnvironmentsRequest extends Request {

	private static final long serialVersionUID = -931614060572700581L;
	private final Action action = Action.GET_INVIRONMENTS;

	@Override
	public Action getAction() {
		return action;
	}

}

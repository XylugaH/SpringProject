package com.xylugah.springcore.messages;

import com.xylugah.springcore.model.Action;

public class EnvironmentsRequest extends Request {

	private static final long serialVersionUID = -5811175054167254613L;
	private Action action;

	public EnvironmentsRequest() {
		super();
	}

	public EnvironmentsRequest(final Action action) {
		setAction(action);
	}

}

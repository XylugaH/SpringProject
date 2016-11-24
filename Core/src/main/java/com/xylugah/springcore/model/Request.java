package com.xylugah.springcore.model;

import java.io.Serializable;

public class Request implements Serializable {

	private static final long serialVersionUID = -267297854755027396L;

	private Action action;

	public Request() {
		super();
	}

	public Request(final Action action) {
		this.action = action;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return this.action.toString();
	}
}

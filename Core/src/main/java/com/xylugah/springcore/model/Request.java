package com.xylugah.springcore.model;

import java.io.Serializable;

public abstract class Request implements Serializable {

	private static final long serialVersionUID = -7375937017804558463L;

	Action action;

	public abstract Action getAction();

}

package com.xylugah.springcore.messages;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.xylugah.springcore.model.Action;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({ @JsonSubTypes.Type(value = EnvironmentsRequest.class, name = "EnvironmentsRequest") })

public abstract class Request implements Serializable {

	private static final long serialVersionUID = -267297854755027396L;

	private Action action;

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}

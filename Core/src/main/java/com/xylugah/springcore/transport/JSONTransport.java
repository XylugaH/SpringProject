package com.xylugah.springcore.transport;

import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xylugah.springcore.model.Request;
import com.xylugah.springcore.model.Response;

public class JSONTransport implements Transport {

	public static final Logger logger = Logger.getLogger(JSONTransport.class);
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Request receiveRequest(Socket socket) {
		String obj = null;
		try {
			obj = (String) receive(socket);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			Request request = mapper.readValue(obj, Request.class);
			return request;
		} catch (IOException e) {
			logger.error("Read request error!", e);
			return null;
		}
	}

	@Override
	public void transmitRequest(Request request, Socket socket) {
		try {
			String obj = mapper.writeValueAsString(request);
			try {
				transmit(obj, socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			logger.error("Output stream error!", e);
		}
	}

	@Override
	public Response receiveResponse(Socket socket) {
		String obj = null;
		try {
			obj = (String) receive(socket);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			Response response = mapper.readValue(obj, Response.class);
			return response;
		} catch (IOException e) {
			logger.error("Read response error!", e);
			return null;
		}
	}

	@Override
	public void transmitResponse(Response response, Socket socket) {
		try {
			String obj = mapper.writeValueAsString(response);
			transmit(obj, socket);
		} catch (JsonProcessingException e) {
			logger.error("Output stream error!", e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

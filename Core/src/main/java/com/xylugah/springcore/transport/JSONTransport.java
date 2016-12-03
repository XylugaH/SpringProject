package com.xylugah.springcore.transport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
		} catch (NullPointerException e) {
			logger.error("Receive request error!", e);
			return null;
		} catch (IOException e) {
			logger.error("Receive request error!", e);
			return null;
		}

		Request request = null;
		try {
			request = mapper.readValue(obj, Request.class);
		} catch (JsonParseException e) {
			logger.error("Parse request error!", e);
			return null;
		} catch (JsonMappingException e) {
			logger.error("Mapping request error!", e);
			return null;
		} catch (IOException e) {
			logger.error("IO error!", e);
			return null;
		}
		return request;

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
		} catch (NullPointerException e) {
			logger.error("Receive Response error!", e);
			return null;
		} catch (IOException e) {
			logger.error("Receive Response error!", e);
			return null;
		}

		Response response = null;
		try {
			response = mapper.readValue(obj, Response.class);
		} catch (JsonParseException e) {
			logger.error("Parse response error!", e);
			return null;
		} catch (JsonMappingException e) {
			logger.error("Mapping Response error!", e);
			return null;
		} catch (IOException e) {
			logger.error("IO error!", e);
			return null;
		}
		return response;
	}

	@Override
	public void transmitResponse(Response response, Socket socket) {
		try {
			String obj = mapper.writeValueAsString(response);
			transmit(obj, socket);
		} catch (JsonProcessingException e) {
			logger.error("Output stream error!", e);
		} catch (IOException e) {
			logger.error("IO error!", e);
		}
	}

	private String receive(final Socket socket) throws IOException, NullPointerException {
		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		int length = inputStream.readInt();
		byte[] message = null;
		if (length > 0) {
			message = new byte[length];
			inputStream.readFully(message, 0, message.length);
		}
		return new String(message);
	}

	private void transmit(final String outputObj, final Socket socket) throws IOException {
		byte[] message = outputObj.getBytes();
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeInt(message.length);
		outputStream.write(message);
		outputStream.flush();
	}
}

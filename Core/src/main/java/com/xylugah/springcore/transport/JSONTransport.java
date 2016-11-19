package com.xylugah.springcore.transport;

import java.net.Socket;

import com.xylugah.springcore.model.Request;
import com.xylugah.springcore.model.Response;

public class JSONTransport implements Transport {

	@Override
	public Request receiveRequest(Socket socket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transmitRequest(Request request, Socket socket) {
		// TODO Auto-generated method stub

	}

	@Override
	public Response reciveResponse(Socket socket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transmitResponse(Response response, Socket socket) {
		// TODO Auto-generated method stub

	}

}

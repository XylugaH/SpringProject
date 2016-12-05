package com.xylugah.springcore.transport;

import java.net.Socket;

import com.xylugah.springcore.messages.Request;
import com.xylugah.springcore.messages.Response;

public interface Transport {
	public Request receiveRequest(final Socket socket);

	public void transmitRequest(final Request request, final Socket socket);

	public Response receiveResponse(final Socket socket);

	public void transmitResponse(final Response response, final Socket socket);
}

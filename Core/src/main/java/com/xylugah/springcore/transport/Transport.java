package com.xylugah.springcore.transport;

import java.net.Socket;

import com.xylugah.springcore.model.Request;
import com.xylugah.springcore.model.Response;

public interface Transport {
	public Request receiveRequest(final Socket socket);

	public void transmitRequest(final Request request, final Socket socket);

	public Response reciveResponse(final Socket socket);

	public void transmitResponse(final Response response, final Socket socket);

}

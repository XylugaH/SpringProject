package com.xylugah.springcore.transport;

import java.net.Socket;

import com.xylugah.springcore.core.Request;
import com.xylugah.springcore.core.Response;

public interface Transport {
	public Request receive(final Socket socket);

	public void transmit(final Response response, final Socket socket);
}

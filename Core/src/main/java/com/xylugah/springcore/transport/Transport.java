package com.xylugah.springcore.transport;

import java.net.Socket;

public interface Transport {
	public Object receive(final Socket socket);

	public void transmit(final Object response, final Socket socket);
}

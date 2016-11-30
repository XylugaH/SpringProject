package com.xylugah.springcore.dao;

import java.util.ArrayList;
import java.util.List;

import com.xylugah.springcore.model.Client;

public class DataMemoryDAO implements DataDAO {

	private List<Client> clientList = new ArrayList<>();

	public DataMemoryDAO() {
		super();
	}

	@Override
	public void add(final String ip, final int port) {
		int id = 0;
		clientList.add(new Client(ip, port, id));

	}

	@Override
	public boolean remove(final int id) {
		try {
			clientList.remove(id);
			return true;
		} catch (IndexOutOfBoundsException ex) {
			return false;
		}
	}

	@Override
	public Client getById(final int id) {
		try {
			Client client = clientList.get(id);
			client.setId(id);
			return client;
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}

	@Override
	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}

}

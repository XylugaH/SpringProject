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
		System.out.println("Save to Memory!!");
		clientList.add(new Client(ip, port));

	}

	@Override
	public void remove(final int id) {
		clientList.remove(id);

	}

	@Override
	public Client getById(final int index) {
		return null;
	}

	@Override
	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}

}

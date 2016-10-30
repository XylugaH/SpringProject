package com.xylugah.springcore.dao;

import java.util.ArrayList;
import java.util.List;

import com.xylugah.springcore.core.Client;

public class DataMongoDBDAO implements IDataDAO {

	private List<Client> clientList = new ArrayList<>();

	@Override
	public void add(final String ip, final int port) {
		System.out.println("Save to Mongo!!");

	}

	@Override
	public void remove(final int id) {
		clientList.remove(id);

	}

	@Override
	public Client getById(int index) {
		return null;
	}

	@Override
	public List<Client> getAll() {
		return this.clientList;

	}

}

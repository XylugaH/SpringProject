package com.xylugah.springcore.dao;

import java.util.List;

import com.xylugah.springcore.model.Client;

public interface DataDAO {
	public void add(final String ip, final int port);

	public void remove(final int id);

	public Client getById(final int index);

	public List<Client> getClientList();

}

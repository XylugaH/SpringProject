package com.xylugah.springcore.dao;

import java.util.List;

import com.xylugah.springcore.core.Client;

public interface IDataDAO {
	public void add(final String ip, final int port);

	public void remove(final int id);

	public Client getById(final int index);

	public List<Client> getAll();

}

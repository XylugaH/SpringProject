package com.xylugah.springcore.dao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import com.xylugah.springcore.model.Client;

public class DataMemoryDAO implements DataDAO {

	public static final Logger logger = Logger.getLogger(DataMemoryDAO.class);
	private List<Client> clientList = new CopyOnWriteArrayList<>();

	public DataMemoryDAO() {
		super();
	}

	@Override
	public void add(final String ip, final int port) {
		clientList.add(new Client(ip, port, 0));
		if (logger.isInfoEnabled()) {
			logger.info("Add client sucessfull!");
		}
	}

	@Override
	public boolean remove(final int id) {
		try {
			clientList.remove(id);
			return true;
		} catch (IndexOutOfBoundsException e) {
			if (logger.isInfoEnabled()) {
				logger.info("Out of index!" + id);
			}
			return false;
		}
	}

	@Override
	public Client getById(final int id) {
		try {
			Client client = clientList.get(id);
			client.setId(id);
			return client;
		} catch (IndexOutOfBoundsException e) {
			if (logger.isInfoEnabled()) {
				logger.info("Out of index!" + id);
			}
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

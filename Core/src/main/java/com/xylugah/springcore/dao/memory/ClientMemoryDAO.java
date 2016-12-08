package com.xylugah.springcore.dao.memory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import com.xylugah.springcore.dao.DataDAO;
import com.xylugah.springcore.entity.Client;

public class ClientMemoryDAO implements DataDAO<Client, Integer> {

	public static final Logger logger = Logger.getLogger(ClientMemoryDAO.class);
	private List<Client> entityList = new CopyOnWriteArrayList<>();

	public ClientMemoryDAO() {
		super();
	}

	@Override
	public void add(final Client entity) {
		entityList.add(entity);
		if (logger.isInfoEnabled()) {
			logger.info("Add client sucessfull!" + entity);
		}
	}

	@Override
	public boolean removeById(final Integer key) {
		try {
			entityList.remove(key);
			if (logger.isInfoEnabled()) {
				logger.info("Remove client sucessfull!");
			}
			return true;
		} catch (IndexOutOfBoundsException e) {
			if (logger.isInfoEnabled()) {
				logger.info("Out of index!" + key);
			}
			return false;
		}
	}

	@Override
	public Client getById(final Integer key) {
		try {
			Client entity = entityList.get(key);
			return entity;
		} catch (IndexOutOfBoundsException e) {
			if (logger.isInfoEnabled()) {
				logger.info("Out of index!" + key);
			}
			return null;
		}
	}

	@Override
	public List<Client> getAll() {
		return entityList;
	}

	public List<Client> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Client> entityList) {
		this.entityList = entityList;
	}

}

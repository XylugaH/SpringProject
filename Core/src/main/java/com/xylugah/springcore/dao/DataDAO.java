package com.xylugah.springcore.dao;

import java.util.List;

public interface DataDAO<T> {
	public void add(final String ip, final int port);

	public boolean remove(final int id);

	public T getById(final int id);

	public List<T> getClientList();

}

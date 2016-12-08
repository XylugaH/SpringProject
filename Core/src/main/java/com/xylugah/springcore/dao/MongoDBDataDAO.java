package com.xylugah.springcore.dao;

import java.util.List;

public interface MongoDBDataDAO<T extends com.xylugah.springcore.entity.Entity> {
	public void add(final T entity);

	public boolean removeById(final int id);

	public T getById(final int id);

	public List<T> getAll();
}
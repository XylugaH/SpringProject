package com.xylugah.springcore.dao;

import java.util.List;

public interface DataDAO<T extends com.xylugah.springcore.entity.Entity, K> {

	public void add(final T entity);

	public boolean removeById(final K key);

	public T getById(final K key);

	public List<T> getAll();

}

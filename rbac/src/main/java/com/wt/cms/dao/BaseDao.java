package com.wt.cms.dao;

import java.io.Serializable;

/**
 * @description:
 * @author wt
 * @date 2017-11-29
 */
public interface BaseDao<T extends Serializable> {
	public void save(T t);
	public void delete(T t);
	public T get(String id);
	public void update(T t);
}

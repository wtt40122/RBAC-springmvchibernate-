package com.wt.cms.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wt.cms.dao.BaseDao;

/**
 * @description:
 * @author wt
 * @date 2017-11-29
 */
@Repository("baseDao")
public class BaseDaoImpl<T extends Serializable> implements BaseDao<T> {
	
	private Class<T> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;

	public BaseDaoImpl() {
		this.entityClass = null;
		Class<? extends BaseDaoImpl> c = getClass();
		Type t = c.getGenericSuperclass();

		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			entityClass = (Class<T>) p[0];
		}
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}

	@Override
	public T get(String id) {
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

}

package com.wt.cms.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wt.cms.dao.UserDao;
import com.wt.cms.entity.PageBean;
import com.wt.cms.model.User;

/**
 * @description:
 * @author wt
 * @date 2017-11-28
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public User Login(User user) {
		String hql  = "FROM User u WHERE u.userName=:userName and u.userPwd=:userPwd";
		Query query = getSession().createQuery(hql);
		query.setParameter("userName", user.getUserName());
		query.setParameter("userPwd", user.getUserPwd());
		return (User)query.uniqueResult();
	}
	@Override
	public List<Map<String,String>> getAllUser(PageBean<Map<String,String>> pageBean) {
		String hql = "SELECT new map(u.userId as userId," +
				"u.userName as userName,u.realName as realName," +
				"u.sex as sex,u.phone as phone,u.email as email," +
				"u.userPwd as password) FROM User u";
		Query query = getSession().createQuery(hql);
		int start = (pageBean.getPageNumber() - 1)*pageBean.getPageSize();
		query.setFirstResult(start);
		query.setMaxResults(pageBean.getPageSize());
		return query.list();
	}
	@Override
	public int getAllUserCount() {
		String hql = "SELECT new map(u.userId as userId," +
				"u.userName as userName,u.realName as realName," +
				"u.sex as sex,u.phone as phone,u.email as email," +
				"u.userPwd as password) FROM User u";
		Query query = getSession().createQuery(hql);
		return query.list().size();
	}
}

package com.wt.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.cms.dao.UserDao;
import com.wt.cms.entity.PageBean;
import com.wt.cms.model.User;
import com.wt.cms.service.UserService;

/**
 * @description:
 * @author wt
 * @date 2017-11-28
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User Login(User user) {
		return userDao.Login(user);
	}
	@Override
	public List<Map<String,String>> getAllUser(PageBean<Map<String,String>> pageBean) {
		return userDao.getAllUser(pageBean);
	}
	@Override
	public void save(User user) {
		userDao.save(user);
	}
	@Override
	public int getAllUserCount() {
		return userDao.getAllUserCount();
	}
	@Override
	public void delete(String id) {
		User user = new User();
		user.setUserId(id);
		userDao.delete(user);
	}
	@Override
	public User getUserById(String uid) {
		return userDao.get(uid);
	}
	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

}

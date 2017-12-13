package com.wt.cms.service;

import java.util.List;
import java.util.Map;

import com.wt.cms.entity.PageBean;
import com.wt.cms.model.User;

/**
 * @description:
 * @author wt
 * @date 2017-11-28
 */
public interface UserService {
	public User Login(User user);
	public List<Map<String,String>> getAllUser(PageBean<Map<String,String>> pageBean);
	public int getAllUserCount();
	public void save(User user);
	
	public void delete(String id);
	
	public User getUserById(String uid);
	
	
	public void updateUser(User user);
}

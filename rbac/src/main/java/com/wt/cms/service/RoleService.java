package com.wt.cms.service;

import java.util.List;
import java.util.Map;

import com.wt.cms.model.Roles;

/**
 * @description:
 * @author wt
 * @date 2017-11-29
 */
public interface RoleService {
	/**
	 * 通过用户Id得到该用户的角色集合
	 * @param uid
	 * @return
	 */
	public List<Roles> getRoleById(String uid);
	
	public List<Map<String,String>> getRoles(int pgeSize,int pageNumber);
	
	public int getRolesCount();
	
	public void save(Roles role);
	
	public Roles getRoleByRid(String rid);
	
	public void deleteRole(String rid);
	
	public void update(Roles role);
	
	public List<Map<String,String>> getAllRoles();
}

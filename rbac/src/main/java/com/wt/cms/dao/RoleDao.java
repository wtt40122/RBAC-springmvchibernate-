package com.wt.cms.dao;

import java.util.List;
import java.util.Map;

import com.wt.cms.model.Roles;

/**
 * @description:
 * @author wt
 * @date 2017-11-29
 */
public interface RoleDao extends BaseDao<Roles> {
	/**
	 * 通过用户Id得到该用户的所有角色
	 * @param uid 用户ID
	 * @return	角色集合
	 */
	public List<Roles> getRoleById(String uid);
	
	public List<Map<String,String>> getRoles(int pgeSize,int pageNumber);
	
	public int getRolesCount();
	
	public List<Map<String,String>> getAllRoles();
}

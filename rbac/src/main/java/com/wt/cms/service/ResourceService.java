package com.wt.cms.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wt.cms.model.Resource;

/**
 * @description:
 * @author wt
 * @date 2017-11-30
 */
public interface ResourceService {
	/**
	 * 根据角色id得到所有权限
	 * @return
	 */
	public Map<String,Object> getPermissionByRid(String rid);
	
	public Set<Resource> listUserPermissions(String uid);
	
	public List<Map<String,String>> getResourceByPid(String pid);
	
	public Resource getResourceById(String rsId);
	
	public void save(Resource resource);
	
	public void delete(String rsId);
	
	public void update(Resource resource);
	
	public List<Map<String,String>> getAllResources();
	
}

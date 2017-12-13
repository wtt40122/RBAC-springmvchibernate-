package com.wt.cms.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wt.cms.model.Resource;

/**
 * @description:
 * @author wt
 * @date 2017-11-30
 */
public interface ResourceDao extends BaseDao<Resource> {
	public Set<Resource> getPermissionByRid(String rid);
	
	public List<Map<String, String>> getResourceByPid(String pid);
	
	public List<Map<String,String>> getAllResources();
	
}

package com.wt.cms.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.cms.dao.ResourceDao;
import com.wt.cms.dao.RoleDao;
import com.wt.cms.model.Resource;
import com.wt.cms.model.Roles;
import com.wt.cms.service.ResourceService;

/**
 * @description:
 * @author wt
 * @date 2017-11-30
 */
@Service
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private RoleDao roleDao;
	
	public Map<String, Object> getPermissionByRid(String rid) {
		Set<Resource> resources = resourceDao.getPermissionByRid(rid);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resources", resources);
		map.put("change", true);
		return map;
	}

	@Override
	public Set<Resource> listUserPermissions(String uid) {
		Set<Resource> resources = new HashSet<Resource>();
		List<Roles> roleIds = roleDao.getRoleById(uid);
		for (int i = 0; i < roleIds.size(); i++) {
			String roleId = roleIds.get(i).getRoleId();
			resources.addAll(resourceDao.getPermissionByRid(roleId));
		}
		return resources;
	}

	@Override
	public List<Map<String, String>> getResourceByPid(String pid) {
		return resourceDao.getResourceByPid(pid);
	}

	@Override
	public Resource getResourceById(String rsId) {
		return resourceDao.get(rsId);
	}

	@Override
	public void save(Resource resource) {
		resourceDao.save(resource);
	}

	@Override
	public void delete(String rsId) {
		Resource resource = new Resource();
		resource.setResourceId(rsId);
		resourceDao.delete(resource);
	}

	@Override
	public void update(Resource resource) {
		resourceDao.update(resource);
	}

	@Override
	public List<Map<String, String>> getAllResources() {
		return resourceDao.getAllResources();
	}

}

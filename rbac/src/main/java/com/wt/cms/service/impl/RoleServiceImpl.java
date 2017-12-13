package com.wt.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.cms.dao.RoleDao;
import com.wt.cms.model.Roles;
import com.wt.cms.service.RoleService;

/**
 * @description:
 * @author wt
 * @date 2017-11-29
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	@Override
	public List<Roles> getRoleById(String uid) {
		return roleDao.getRoleById(uid);
	}
	@Override
	public List<Map<String, String>> getRoles(int pgeSize, int pageNumber) {
		return roleDao.getRoles(pgeSize, pageNumber);
	}
	@Override
	public int getRolesCount() {
		return roleDao.getRolesCount();
	}
	@Override
	public void save(Roles role) {
		roleDao.save(role);
	}
	@Override
	public Roles getRoleByRid(String rid) {
		return roleDao.get(rid);
	}
	@Override
	public void deleteRole(String rid) {
		Roles role = new Roles();
		role.setRoleId(rid);
		roleDao.delete(role);
	}
	@Override
	public void update(Roles role) {
		roleDao.update(role);
	}
	@Override
	public List<Map<String, String>> getAllRoles() {
		return roleDao.getAllRoles();
	}

}

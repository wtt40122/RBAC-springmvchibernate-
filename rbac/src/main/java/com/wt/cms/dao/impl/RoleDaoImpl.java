package com.wt.cms.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wt.cms.dao.RoleDao;
import com.wt.cms.model.Roles;
import com.wt.cms.model.User;

/**
 * @description:
 * @author wt
 * @date 2017-11-29
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Roles> implements RoleDao {

	@Override
	public List<Roles> getRoleById(String uid) {
		List<Roles> roleIds = new ArrayList<>();
		User user = (User) getSession().get(User.class, String.valueOf(uid));
		Iterator<Roles> sdfds = user.getRoles().iterator();
		while(sdfds.hasNext()){
			Roles role = sdfds.next();
			roleIds.add(role);
		}
		return roleIds;
	}

	@Override
	public List<Map<String, String>> getRoles(int pgeSize, int pageNumber) {
		String hql = "SELECT new map(r.roleId as roleId,r.role_name as roleName,r.remark as remark" +
				") FROM Roles r ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult((pageNumber-1)*pgeSize);
		query.setMaxResults(pgeSize);
		return query.list();
	}

	@Override
	public int getRolesCount() {
		String hql = "select r from Roles r";
		Query query = getSession().createQuery(hql);
		return query.list().size();
	}

	@Override
	public List<Map<String, String>> getAllRoles() {
		String hql = "SELECT new map(r.roleId as roleId,r.role_name as roleName,r.remark as remark" +
				") FROM Roles r ";
		return getSession().createQuery(hql).list();
	}

}

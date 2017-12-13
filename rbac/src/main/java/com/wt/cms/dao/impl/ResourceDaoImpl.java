package com.wt.cms.dao.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wt.cms.dao.ResourceDao;
import com.wt.cms.model.Resource;
import com.wt.cms.model.Roles;

/**
 * @description:
 * @author wt
 * @date 2017-11-30
 */
@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {

	@Override
	public Set<Resource> getPermissionByRid(String rid) {
		Roles role = (Roles)getSession().get(Roles.class, rid);
		Iterator<Resource> iterator = role.getResources().iterator();
		Set<Resource> resources = new HashSet<Resource>();
		while(iterator.hasNext()){
			Resource resource = iterator.next();
			resources.add(resource);
		}
		return resources;
	}

	public List<Resource> listUserPermissions(String uid) {
		String hql = "From Resource r where r.roles.users.userId =:userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", uid);
		return query.list();
	}

	@Override
	public List<Map<String, String>> getResourceByPid(String pid) {
		String hql="SELECT new map(r.resourceId as resourceId,r.resouceName as resouceName,r.resouceUrl as resouceUrl," +
				"r.pid as pid,r.state as state) FROM Resource r where r.pid =:pid";
		Query query = getSession().createQuery(hql).setParameter("pid", pid);
		return query.list();
	}

	@Override
	public List<Map<String, String>> getAllResources() {
		String hql="select new map(r.resourceId as resourceId,r.resouceName as resouceName,r.resouceUrl as resouceUrl," +
				"r.pid as pid,r.state as state) from Resource r";
		return getSession().createQuery(hql).list();
	}

}

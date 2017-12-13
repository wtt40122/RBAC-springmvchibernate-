package com.wt.cms.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * @description:
 * @author wt
 * @date 2017-11-20
 */
@Entity
@Table(name = "role")
public class Roles implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@Column(length = 32)
	private String roleId;				//用户主键
	
	@Column(length = 100)				//角色名称
	private String role_name;
	
	@Column(length = 100)				//备注
	private String remark;
	
	@ManyToMany
	@Cascade(value={CascadeType.SAVE_UPDATE})	//设定级联关系
	@JoinTable(name="role_user",
				joinColumns={@JoinColumn(name="role_id")},
				inverseJoinColumns={@JoinColumn(name="user_id")})
	private Set<User> users = new HashSet<User>();
	
	@ManyToMany
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinTable(name="role_resource",
				joinColumns={@JoinColumn(name="role_id")},
				inverseJoinColumns={@JoinColumn(name="resource_id")})
	public Set<Resource> resources = new HashSet<Resource>();
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "Roles [roleId=" + roleId + ", role_name=" + role_name
				+ ", remark=" + remark + ", users=" + users + ", resources="
				+ resources + "]";
	}
	
}

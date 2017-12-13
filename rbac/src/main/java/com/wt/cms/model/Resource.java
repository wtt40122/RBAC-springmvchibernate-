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
 * @date 2017-11-28
 */
@Entity
@Table(name="resource")
public class Resource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@Column(length=32)
	private String resourceId;				//权限主键
	
	@Column(name="resource_name",length=32)
	private String resouceName;				//权限名称
	
	@Column(name="resource_url",length=32)
	private String resouceUrl;				//权限Url
	
	@Column(length=32)
	private String pid;
	
	@Column(length=32)
	private String action;
	
	@Column(length=32)
	private String icon;
	
	private String remark;					//备注
	
	private String state;
	
	@ManyToMany
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinTable(name="role_resource",									//指定中间表
				joinColumns={@JoinColumn(name="resource_id")},			//本表与中间表的外键对应
				inverseJoinColumns={@JoinColumn(name="role_id")})		//另一张表与中间表的外键对应
	private Set<Roles> roles = new HashSet<Roles>();

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResouceName() {
		return resouceName;
	}

	public void setResouceName(String resouceName) {
		this.resouceName = resouceName;
	}

	public String getResouceUrl() {
		return resouceUrl;
	}

	public void setResouceUrl(String resouceUrl) {
		this.resouceUrl = resouceUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}

package com.wt.cms.dto;


/**
 * @description:
 * @author wt
 * @date 2017-12-6
 */
public class RoleDto {
	private String id;
	private String pid;
	private String name;
	private boolean open=true;
	public RoleDto() {
	}
	
	public RoleDto(String id, String pid, String name, boolean open) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.open = open;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return "RoleDto [id=" + id + ", pid=" + pid + ", name=" + name
				+ ", open=" + open + "]";
	}
}

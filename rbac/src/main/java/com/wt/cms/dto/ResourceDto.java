package com.wt.cms.dto;

import com.wt.cms.model.Resource;

/**
 * @description:
 * @author wt
 * @date 2017-12-7
 */
public class ResourceDto extends Resource {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}

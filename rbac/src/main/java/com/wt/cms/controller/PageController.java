package com.wt.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author wt
 * @date 2017-11-29
 */
@Controller
public class PageController {
	@RequestMapping("/login")
	public String login() {
		return "hello";
	}

	@RequestMapping("/success")
	public String success() {
		return "success";
	}

	@RequestMapping("/quit")
	public String quit() {
		return "quit";
	}

	/**
	 * 增加用户
	 */
	@RequestMapping("/page/addOne")
	public String addOne() {
		return "add";
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/page/deleteOne")
	public String deleteOne() {
		return "delete";
	}

	/**
	 * 查询用户
	 */
	@RequestMapping("/page/selectOne")
	public String selectOne() {
		return "select";
	}

	/**
	 * 更新用户
	 */
	@RequestMapping("/page/updateOne")
	public String updateOne() {
		return "update";
	}

	/**
	 * 用户注册
	 */
	@RequestMapping("/page/submit")
	public String submit() {
		return "submit";
	}

	/**
	 * 添加权限
	 */
	@RequestMapping("/page/change")
	public String addPermission() {
		return "change";
	}

	/**
	 * 更新权限
	 */
	@RequestMapping("/page/flushPermission")
	public String flushPermission() {
		return "flush";
	}

	/**
	 * 得到角色列表
	 */
	@RequestMapping("/page/getRole")
	public String getRole() {
		return "flushRole";
	}
}

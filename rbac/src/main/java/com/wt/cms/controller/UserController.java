package com.wt.cms.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wt.cms.entity.PageBean;
import com.wt.cms.model.Resource;
import com.wt.cms.model.User;
import com.wt.cms.service.ResourceService;
import com.wt.cms.service.RoleService;
import com.wt.cms.service.UserService;

/**
 * @description:
 * @author wt
 * @date 2017-11-28
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	public RoleService roleService;

	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	public ModelAndView login(User user, ModelMap modelMap,
			HttpServletRequest request) {

		User loginUser = userService.Login(user);
		if (loginUser == null) {
			return null;
		}

		// 根据用户Id得到用户的权限集合
		Set<Resource> resources = resourceService.listUserPermissions(loginUser
				.getUserId());

		ModelAndView model = new ModelAndView();
		// model.addObject("resources", resources);
		// model.addObject("user", loginUser);
		modelMap.put("user", loginUser);
		modelMap.put("resources", resources);

		HttpSession session = request.getSession();
		session.setAttribute("userInfo", loginUser.getUserName());

		modelMap.put("roles", roleService.getRoleById(loginUser.getUserId()));
		model.setViewName("main");
		return model;
	}

	@RequestMapping("user/getResource")
	public String getResourceByRid(@RequestParam("rid") String rid) {
		Map<String, Object> maps = resourceService.getPermissionByRid(rid);
		System.out.println(maps);
		return "";
	}

	@RequestMapping(value = "/user/usermanage", method = RequestMethod.GET)
	public String userManage() {
		return "user/userManage";
	}

	@ResponseBody
	@RequestMapping(value = "/user/getAllUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String getAllUsr(
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "rows", required = false) int rows) {
		PageBean<Map<String, String>> pageBean = new PageBean<Map<String, String>>();
		pageBean.setPageNumber(page);
		pageBean.setPageSize(rows);
		List<Map<String, String>> users = userService.getAllUser(pageBean);
		pageBean.setRows(users);
		pageBean.setTotal(userService.getAllUserCount());
		JSONObject json = JSONObject.fromObject(pageBean);
		return json.toString();
	}

	@RequestMapping(value = "/user/addUser", method = RequestMethod.GET)
	public String addUser() {
		return "user/add";
	}

	@ResponseBody
	@RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
	public String addUsers(User user) {
		userService.save(user);
		return "success";
	}

	@ResponseBody
	@RequestMapping("/user/deleteUsers")
	public String deleteUsers(@RequestParam("ids") String ids) {
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			userService.delete(idsStr[i]);
		}
		return "success";
	}

	@RequestMapping(value = "/user/updateUser/{userId}", method = RequestMethod.GET)
	public ModelAndView updateUser(@PathVariable String userId) {
		User user = userService.getUserById(userId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("user/update");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/user/updateUser", method = RequestMethod.POST)
	public String updateusers(User user) {
		userService.updateUser(user);
		return "success";
	}

}

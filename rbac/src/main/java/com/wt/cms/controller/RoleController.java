package com.wt.cms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
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

import com.wt.cms.dto.RoleDto;
import com.wt.cms.entity.PageBean;
import com.wt.cms.model.Roles;
import com.wt.cms.model.User;
import com.wt.cms.service.RoleService;
import com.wt.cms.service.UserService;

/**
 * @description:
 * @author wt
 * @date 2017-11-29
 */
@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	@RequestMapping("user/getRoleById")
	public String getRoleById(@RequestParam("uid") String uid) {
		//List<Roles> roleById = roleService.getRoleById(uid);
		return "";
	}
	@RequestMapping(value="/role/getRoles",method=RequestMethod.GET)
	public String getRole(){
		return "role/list";
	}
	@ResponseBody
	@RequestMapping(value="/role/getRoles",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public String getRoles(@RequestParam(value="page",required=false)int page,
						   @RequestParam(value="rows",required=false)int rows) throws IOException{
		List<Map<String, String>> roles = roleService.getRoles(rows, page);
		PageBean<Map<String,String>> pageBean = new PageBean<Map<String,String>>();
		int rolesCount = roleService.getRolesCount();
		pageBean.setTotal(rolesCount);
		pageBean.setRows(roles);
		JSONObject json = JSONObject.fromObject(pageBean);
		return json.toString();
	}
	@RequestMapping(value="/role/add",method=RequestMethod.GET)
	public String addRole(){
		return "role/add";
	}
	@ResponseBody
	@RequestMapping(value="/role/add",method=RequestMethod.POST)
	public String addRoles(Roles role){
		if(role == null){
			return "fail";
		}
		roleService.save(role);
		return "success";
	}
	@ResponseBody
	@RequestMapping(value="/role/delete",method=RequestMethod.POST)
	public String deleteRoles(@RequestParam("ids")String ids){
		String[] idStr = ids.split(",");
		for (int i = 0; i < idStr.length; i++) {
			roleService.deleteRole(idStr[i]);
		}
		return "success";
	}
	
	@RequestMapping("/role/update/{roleId}")
	public ModelAndView updateRole(@PathVariable String roleId,ModelMap modelMap){
		Roles role = roleService.getRoleByRid(roleId);
		modelMap.addAttribute("role", role);
		return new ModelAndView("role/update", modelMap);
	}
	@ResponseBody
	@RequestMapping(value="/role/update",method=RequestMethod.POST)
	public String update(Roles role){
		if(role == null){
			return "fail";
		}
		roleService.update(role);
		return "success";
	}
	@RequestMapping(value="/role/getRoles/{uId}",method=RequestMethod.GET)
	public ModelAndView getRoles(@PathVariable String uId){
		List<Roles> roles = roleService.getRoleById(uId);
		List<String> roleIds = new ArrayList<String>();
		for (int i = 0; i < roles.size(); i++) {
			roleIds.add(roles.get(i).getRoleId());
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("role/roleTree");
		modelAndView.addObject("role", JSONArray.fromObject(roleIds).toString());
		modelAndView.addObject("userId", uId);
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/role/getAllRoles",produces="application/json;charset=utf-8")
	public String getAllRoles(){
		List<Map<String, String>> roles = roleService.getAllRoles();
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		RoleDto dto = new RoleDto("010", "00", "用户角色", true);
		dtos.add(dto);
		for (int i = 0; i < roles.size(); i++) {
			RoleDto roleDto = new RoleDto();
			roleDto.setId(roles.get(i).get("roleId"));
			roleDto.setName(roles.get(i).get("roleName"));
			roleDto.setPid("010");
			dtos.add(roleDto);
		}
		JSONArray jsonArray = JSONArray.fromObject(dtos);
		return jsonArray.toString();
	}
	@ResponseBody
	@RequestMapping(value="/role/setRoles",method=RequestMethod.POST)
	public String setRoles(String userId,@RequestParam(value="role[]")String[] role){
		User user = userService.getUserById(userId);
		Set<Roles> roles = user.getRoles();
		roles.clear();
		for (int i = 0; i < role.length; i++) {
			Roles roleTemp = roleService.getRoleByRid(role[i]);
			roles.add(roleTemp);
		}
		user.setRoles(roles);
		userService.updateUser(user);
		return "success";
	}
	@RequestMapping("/role/assignPermissions/{rId}")
	public String assignPermission(@PathVariable("rId")String rid){
		return "";
	}
}

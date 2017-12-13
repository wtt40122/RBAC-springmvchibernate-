package com.wt.cms.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.fabric.xmlrpc.base.Array;
import com.wt.cms.dto.ResourceDto;
import com.wt.cms.model.Resource;
import com.wt.cms.model.Roles;
import com.wt.cms.service.ResourceService;
import com.wt.cms.service.RoleService;

/**
 * @description:
 * @author wt
 * @date 2017-12-7
 */
@Controller
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/resource/resources",method=RequestMethod.GET)
	public String resources(){
		return "menu/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/resource/list",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public String getResources(@RequestParam(value="id",required=false)String id){
		StringBuilder stringBuilder = new StringBuilder();
		if(id == null){
			ResourceDto resource = new ResourceDto();
			resource.setResourceId("0");
			resource.setResouceName("资源管理");
			resource.setResouceUrl("#");
			resource.setState("closed");
			JSONObject jsonObject = JSONObject.fromObject(resource);
			stringBuilder.append("[");
			stringBuilder.append(jsonObject.toString());
			stringBuilder.append("]");
		}else{
			List<Map<String, String>> resources = resourceService.getResourceByPid(id);
			JSONArray jsonArray = JSONArray.fromObject(resources);
			stringBuilder.append(jsonArray.toString());
		}
		
		return stringBuilder.toString();
	}
	@RequestMapping(value="/resource/add")
	public ModelAndView addResource(@RequestParam("id")String id){
		ModelAndView modelAndView = new ModelAndView();
		if(id.equals("0")){
			modelAndView.addObject("resName", "资源管理");
			modelAndView.addObject("state", "closed");
		}else{
			Resource resource = resourceService.getResourceById(id);
			modelAndView.addObject("resName", resource.getResouceName());
			modelAndView.addObject("state", resource.getState());
		}
		modelAndView.addObject("pid", id);
		modelAndView.setViewName("menu/add");
		
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/resource/add",method=RequestMethod.POST)
	public String resourceAdd(Resource resource){
		if(resource == null){
			return "fail";
		}
		resourceService.save(resource);
		return "success";
	}
	@ResponseBody
	@RequestMapping(value="/resource/del",method=RequestMethod.POST)
	public String delResource(@RequestParam("id")String id){
		List<Map<String, String>> resources = resourceService.getResourceByPid(id);
		if(resources.size() == 0){
			resourceService.delete(id);
			return "success";
		}else{
			return "fail";
		}
	}
	@RequestMapping(value="/resource/update/{id}",method=RequestMethod.GET)	
	public ModelAndView update(@PathVariable String id){
		Resource resource = resourceService.getResourceById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resource", resource);
		modelAndView.setViewName("menu/update");
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/resource/update",method=RequestMethod.POST)
	public String updateResource(Resource resource){
		if(resource == null){
			return "fail";
		}
		resourceService.update(resource);
		return "success";
	}
	@RequestMapping("resource/assignPermissions/{rId}")
	public ModelAndView assignPermissions(@PathVariable("rId")String rid){
		List<String> rsIds = new ArrayList<>();
		if(rid != null){
			Map<String, Object> permissionRows = resourceService.getPermissionByRid(rid);
			Set<Resource> resources = (Set<Resource>) permissionRows.get("resources");
			Iterator<Resource> iterator = resources.iterator();
			while(iterator.hasNext()){
				Resource resource = iterator.next();
				rsIds.add(resource.getResourceId());
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/menu/menuTree");
		modelAndView.addObject("resIds", JSONArray.fromObject(rsIds));
		modelAndView.addObject("rid", rid);
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/resource/getresources",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public String getAllResources(){
		List<Map<String, String>> resources = resourceService.getAllResources();
		JSONArray fromObject = JSONArray.fromObject(resources);
		return fromObject.toString();
	}
	@ResponseBody
	@RequestMapping(value="/resources/assignResources",method=RequestMethod.POST)
	public String assignPermissions(@RequestParam(value="resource[]")String[] resource,String rid){
		Roles role=null;
		if(rid != null){
			role = roleService.getRoleByRid(rid);
			Set<Resource> resources = role.getResources();
			resources.clear();
			for(int i=0;i<resource.length;i++){
				Resource resourcetemp = resourceService.getResourceById(resource[i]);
				resources.add(resourcetemp);
			}
			role.setResources(resources);
			roleService.update(role);
			return "success";
		}
		return "fail";
	}
}

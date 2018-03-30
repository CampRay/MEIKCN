package com.nuvomed.action;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.commons.ConvertTools;
import com.nuvomed.commons.MyException;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.Tgroup;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.AdminInfoService;
import com.nuvomed.service.AdminRoleService;
import com.nuvomed.service.AdminUserService;
import com.nuvomed.service.GroupService;
import com.nuvomed.service.GroupUserService;

@Controller
@RequestMapping(value="group")
public class GroupController extends BaseController {
		
	@Resource
	private AdminUserService adminUserService;
	
	@Resource
	private AdminInfoService adminInfoService;
	
	@Resource
	private GroupService groupService;
	
	@Resource
	private GroupUserService groupUserService;
	
	@Resource
	private AdminRoleService adminRoleService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView groups(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();		
		TadminUser tUser=getSessionUser(request);
		if(tUser!=null){					
			mav.addObject("adminList", adminUserService.loadAllAdminUserList());
			mav.setViewName("group/groups");
		}
		else{
			tUser=new TadminUser();		
			mav.addObject("user", tUser);
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping(value="groupList",method=RequestMethod.GET)
	@ResponseBody
	public String groupList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=groupService.loadGroupsList(dtp);
		
		pagingData.setSEcho(dtp.sEcho);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
			
	}
	
	/**
	 * <p>Description: 处理新增数据的ajax请求</p>
	 * @Title: addGroup 
	 * @param jsonStr
	 * @param request
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="addGroup",method=RequestMethod.POST)
	@ResponseBody
	public String addGroup(HttpServletRequest request,Tgroup group){		
		JSONObject respJson = new JSONObject();
		try{
			if(groupService.loadGroupByName(group.getGroupName())==null){			
				group.setCreatedTime(System.currentTimeMillis());;
				group.setDeleted(false);							
				groupService.createGroup(group);
				respJson.put("status", true);
			}
			else{
				respJson.put("status", false);
				respJson.put("info", getMessage(request,"system.group.name.exist"));
			}
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}				
		return JSON.toJSONString(respJson);
	}
	
	@RequestMapping(value="editGroup",method=RequestMethod.POST)
	@ResponseBody
	public String updateGroup(HttpServletRequest request,Tgroup group){						
		JSONObject respJson = new JSONObject();		
		try{	
			Tgroup temp=groupService.loadGroupByName(group.getGroupName());
			//如果修改后的组名已经存在，则不能修改
			if(temp==null||temp.getId()==group.getId()){
				temp=groupService.getGroupById(group.getId());
				temp.setGroupName(group.getGroupName());
				temp.setGroupInfo(group.getGroupInfo());
				groupService.updateGroup(temp);
				respJson.put("status", true);
			}
			else{				
				respJson.put("status", false);
				respJson.put("info", getMessage(request,"system.group.name.exist"));
			}
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		String str=JSON.toJSONString(respJson);		
		return str;
	}
	
	@RequestMapping(value="delete/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteGroups(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			groupService.deleteGroupsByIds(idArr);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
		
		
	@RequestMapping(value="activate/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String activate(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);
		JSONObject respJson = new JSONObject();
		try{
			groupService.activateByIds(idArr);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
	
	@RequestMapping(value="deactivate/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String deactivate(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");	
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);
		JSONObject respJson = new JSONObject();
		try{
			groupService.deactivateByIds(idArr);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}

	
}

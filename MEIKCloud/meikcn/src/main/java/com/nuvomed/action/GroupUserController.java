package com.nuvomed.action;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.commons.ConvertTools;
import com.nuvomed.commons.MyException;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.TgroupUser;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.AdminInfoService;
import com.nuvomed.service.AdminRoleService;
import com.nuvomed.service.AdminUserService;
import com.nuvomed.service.GroupService;
import com.nuvomed.service.GroupUserService;

@Controller
@RequestMapping(value="groupuser")
public class GroupUserController extends BaseController {
		
	
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
	
	
//	@RequestMapping(method=RequestMethod.GET)
//	public ModelAndView groups(HttpServletRequest request){
//		ModelAndView mav=new ModelAndView();				
//		mav.addObject("rolesList", adminRoleService.getAllAdminRoles());
//		mav.setViewName("group/groupusers");
//		return mav;
//	}
	
	@RequestMapping(value="groupUserList/{groupId}",method=RequestMethod.GET)
	@ResponseBody
	public String groupUserList(HttpServletRequest request,@PathVariable String groupId,DataTableParamter dtp){		
		PagingData pagingData=groupUserService.loadGroupUserListById(groupId, dtp);		
		pagingData.setSEcho(dtp.sEcho);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		
		String userListJson= JSON.toJSONString(pagingData);
		return userListJson;
			
	}
	
	/**
	 * <p>Description: 处理新增数据的ajax请求</p>
	 * @Title: addGroupUser 
	 * @param jsonStr
	 * @param request
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="addGroupUser",method=RequestMethod.POST)
	@ResponseBody
	public String addGroupUser(HttpServletRequest request,TgroupUser groupUser){	
		TadminUser admin=getSessionUser(request);
		JSONObject respJson = new JSONObject();
		try{
			TadminUser user=groupUser.getAdminUser();
			TadminUser adminuser=adminUserService.getAdminUserById(user.getAdminId());
			if(adminuser!=null){
				List<TgroupUser> groupUserList=groupUserService.getGroupUser(adminuser.getAdminId());
				//只有医生可以存在于多个组中
				if(groupUserList==null||groupUserList.size()==0||adminuser.getAdminRole().getRoleId()==3){
					groupUser.setCreatedTime(System.currentTimeMillis());
					groupUser.setAdminId(admin.getAdminId());			
					groupUserService.createGroupUser(groupUser);
					respJson.put("status", true);
				}								
				else{
					respJson.put("status", false);
					respJson.put("info", getMessage(request,"system.group.adduser.existed",null));
				}
			}
			else{
				respJson.put("status", false);				
			}												
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}				
		return JSON.toJSONString(respJson);
	}		
	
	@RequestMapping(value="delete/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteGroupUsers(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			groupUserService.deleteGroupUsersByIds(idArr);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
		
	
	
}

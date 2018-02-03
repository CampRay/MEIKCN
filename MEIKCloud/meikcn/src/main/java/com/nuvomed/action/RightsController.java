/**   
 * @Title: RightsController.java 
 * @Package com.uswop.action 
 * @Description: TODO
 * @author Phills Li    
 * @date Sep 2, 2014 7:25:22 PM 
 * @version V1.0   
 */
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
import com.nuvomed.dto.TadminNodes;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.AdminNodesService;

/**
 * @ClassName: RightsController
 * @Description: TODO
 * @author Phills Li
 * @date Sep 2, 2014 7:25:22 PM
 * 
 */
@Controller
@RequestMapping(value="/rights")
public class RightsController extends BaseController {	
	
	@Resource
	private AdminNodesService adminNodesService;
		

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView rights(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		TadminUser tUser=getSessionUser(request);
		if(tUser!=null){
			mav.setViewName("rights/rights");
	    }
		else{
			tUser=new TadminUser();		
			mav.addObject("user", tUser);
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public String rightsList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=adminNodesService.loadAdminNodesList(dtp);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		pagingData.setSEcho(dtp.sEcho);
		
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
	}
		
	
	/**
	 * <p>Description: 处理新增数据的ajax请求</p>
	 * @Title: addRights 
	 * @param jsonStr
	 * @param request
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public String addRights(HttpServletRequest request,TadminNodes adminNode){			
		JSONObject respJson = new JSONObject();
		try{
			adminNodesService.createAdminNode(adminNode);			
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}		
		return JSON.toJSONString(respJson);
	}
	
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public String updateRights(HttpServletRequest request,TadminNodes adminNode){		

		JSONObject respJson = new JSONObject();
		try{
			adminNodesService.updateAdminNode(adminNode);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);		
	}

	@RequestMapping(value="delete/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteRights(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			adminNodesService.deleteAdminNodesByIds(idArr);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
}

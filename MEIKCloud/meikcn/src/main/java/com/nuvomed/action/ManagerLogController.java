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
import com.nuvomed.dto.TadminLog;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.AdminuserLogService;


@Controller
@RequestMapping(value="managerlog")
public class ManagerLogController extends BaseController {
		
	@Resource
	private AdminuserLogService adminuserLogService;
		
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView adminuserslog(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();					
		mav.setViewName("managerlog/managerslog");
		return mav;
	}	
	@RequestMapping(value="managerslogList",method=RequestMethod.GET)
	@ResponseBody
	public String AdminuserlogsList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=adminuserLogService.loadAdminLogList(dtp);
		pagingData.setSEcho(dtp.sEcho);	
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
			
		}
	
	@RequestMapping(value="managerslogList/{ids}",method=RequestMethod.GET)
	@ResponseBody
	public String AdminuserlogsList(HttpServletRequest request,@PathVariable String ids,DataTableParamter dtp){		
		
		PagingData pagingData=adminuserLogService.loadAdminLogList(ids, dtp);
		pagingData.setSEcho(dtp.sEcho);	
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}	
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
			
		}
	@RequestMapping(value="managerslog/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteAdminuserslog(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			adminuserLogService.deleteRuleLogById(idArr);
			respJson.put("status", true);
			respJson.put("info", getMessage(request,"operate.success"));
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
	@RequestMapping(value="managerslogview/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String viewAdminuserLogs(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			TadminLog adminslog=adminuserLogService.getRuleLogById(idArr[0]);
			respJson.put("status", true);
			respJson.put("adminslog", adminslog);
			respJson.put("info", getMessage(request,"operate.success"));
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
}



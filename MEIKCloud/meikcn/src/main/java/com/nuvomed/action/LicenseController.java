package com.nuvomed.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.commons.MyException;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.Tlicense;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.LicenseService;


@Controller
@RequestMapping("/license")
public class LicenseController extends BaseController{
	
	@Autowired
	private LicenseService licenseService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView license(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		TadminUser tUser=getSessionUser(request);
		if(tUser!=null){
			mav.setViewName("license/licenses");
		}
		else{
			tUser=new TadminUser();		
			mav.addObject("user", tUser);
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public String licenseList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=licenseService.loadLicenseList(dtp);
		
		pagingData.setSEcho(dtp.sEcho);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;	
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addLicense(HttpServletRequest request,Tlicense license){
	
		JSONObject respJson = new JSONObject();
		try{			
			license.setLicense(UUID.randomUUID().toString().substring(0, 23));
			license.setStatus(true);
			license.setCreatedTime(System.currentTimeMillis());
			if(license.getDeadlineStr()!=null&&!license.getDeadlineStr().isEmpty()){
				SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
				Date date=sdf.parse(license.getDeadlineStr());
				license.setDeadline(date.getTime());
			}
			licenseService.createLicense(license);			
			respJson.put("status", true);
		}
		catch(Exception be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}		
		return JSON.toJSONString(respJson);
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public String editLicense(HttpServletRequest request,Tlicense license){
	
		JSONObject respJson = new JSONObject();
		try{	
			if(license.getDeadlineStr()!=null&&!license.getDeadlineStr().isEmpty()){
				SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
				Date date=sdf.parse(license.getDeadlineStr());
				license.setDeadline(date.getTime());
			}
			else{
				license.setDeadline(null);
			}
			if(license.getCreatedTime()==null||license.getCreatedTime()==0){
				license.setCreatedTime(null);
			}
			licenseService.updateLicense(license);			
			respJson.put("status", true);
		}
		catch(Exception be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}		
		return JSON.toJSONString(respJson);
	}
	
	@RequestMapping(value="/delete/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteLicenses(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");					
		JSONObject respJson = new JSONObject();
		try{
			licenseService.deleteLicenseByIds(idstrArr);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
	@RequestMapping(value="/active/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String activeLicenses(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");				
		JSONObject respJson = new JSONObject();
		try{
			licenseService.activeLicenseByids(idstrArr);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
	
	@RequestMapping(value="/deactive/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String deactiveLicenses(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
			
		JSONObject respJson = new JSONObject();
		try{
			licenseService.deactiveLicenseByids(idstrArr);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
}

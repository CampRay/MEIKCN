package com.nuvomed.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.commons.MyException;
import com.nuvomed.commons.SystemConfig;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.Tsetting;
import com.nuvomed.service.SystemSettingService;


@Controller
@RequestMapping(value="/settings")
public class SettingController extends BaseController {	
	
	@Resource
	private SystemSettingService systemSettingService;
		

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView settings(HttpServletRequest request){
		List<String> list = null;
		Map<String, List<String>> system_setting =new LinkedHashMap<String, List<String>>();
		ModelAndView mav=new ModelAndView();
		try{
			List<Tsetting> settingList = systemSettingService.getAllSystemSetting();
			for (Tsetting setting : settingList) {
				list = new ArrayList<String>();				
				list.add(new String(setting.getValue(),"UTF-8"));
				list.add(setting.getDescr());
				system_setting.put(setting.getName(), list);			
			}			
		}catch(MyException m){
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TadminUser tUser=getSessionUser(request);
		if(tUser!=null){
			mav.addObject("system_setting",SystemConfig.Admin_Setting_Map);
		    mav.setViewName("settings/systemsetting");
		}
		else{
			tUser=new TadminUser();		
			mav.addObject("user", tUser);
			mav.setViewName("login");
		}
		return mav;
	}
	   
	
	@RequestMapping(value="editsetting",method=RequestMethod.POST)
	@ResponseBody
	public String editSettings(HttpServletRequest request,String name,String value){
		JSONObject resp = new JSONObject();
		
		try{
			Tsetting tsetting = systemSettingService.getSystemSettingByName(name);
			if(tsetting != null){				
				tsetting.setValue(value.getBytes("UTF-8"));
	            systemSettingService.updateSystemsetting(tsetting);
	            systemSettingService.cachedSystemSettingData();
			}			
		}catch(MyException m){
			resp.put("status", false);
		} catch (UnsupportedEncodingException e) {
			resp.put("status", false);
			e.printStackTrace();
		} catch (IOException e) {
			resp.put("status", false);
			e.printStackTrace();
		}
		resp.put("status", true);
		return JSON.toJSONString(resp);
	}
		 
	 
    @RequestMapping(value="locale",method=RequestMethod.GET)
	public void setLocale(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	//String local=(String)request.getParameter("locale");
    	//changeLocale(request,local);
    	request.getSession().removeAttribute("locale");			
		Locale locale=LocaleContextHolder.getLocale();
		request.getSession().setAttribute("locale", locale);
		String referer = request.getHeader("referer");
		response.sendRedirect(referer);
	}
	 
}

package com.nuvomed.action;

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
import com.nuvomed.commons.ConvertTools;
import com.nuvomed.commons.MyException;
import com.nuvomed.dto.Tlanguage;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.LanguageService;


@Controller
@RequestMapping("/language")
public class LanguageController extends BaseController{
	
	@Autowired
	private LanguageService languageService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView Languages(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("language/language");		
		return mav;
	}
	
	@RequestMapping(value="/languagelist",method=RequestMethod.GET)
	@ResponseBody
	public String LanguagesList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=languageService.loadLanguageList(dtp);
		
		pagingData.setSEcho(dtp.sEcho);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;	
	}
	
	@RequestMapping(value="/addlanguage",method=RequestMethod.POST)
	@ResponseBody
	public String addLanguages(HttpServletRequest request,Tlanguage language){
	
		JSONObject respJson = new JSONObject();
		try{
			String flagname=language.getLocal().split("_")[1];
			String flagurl="../assets/global/img/flags/"+flagname.toLowerCase()+".png";
			language.setFlagImage(flagurl);
			languageService.createLanguage(language);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}		
		return JSON.toJSONString(respJson);
	}
	@RequestMapping(value="/editlanguage",method=RequestMethod.POST)
	@ResponseBody
	public String editLanguages(HttpServletRequest request,Tlanguage language){
		JSONObject respJson=new JSONObject();
		try {
			String flagname=language.getLocal().split("_")[1];
			String flagurl="../assets/global/img/flags/"+flagname.toLowerCase()+".png";
			language.setFlagImage(flagurl);
			languageService.updateLanguage(language);
			respJson.put("status", true);
		} catch (MyException be) {
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}
		return JSON.toJSONString(respJson);
	}
	@RequestMapping(value="/deletelanguage/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteLanguage(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			languageService.deleteLanguageByIds(idArr);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
	@RequestMapping(value="/activelanguage/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String activelanguages(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			languageService.activeLanguageByids(idArr);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
}

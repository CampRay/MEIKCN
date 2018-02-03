/**   
 * @Title: RecordsController.java 
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
import com.nuvomed.dto.TadminUser;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.RecordsService;

/**
 * @ClassName: RecordsController
 * @Description: TODO
 * @author Phills Li
 * @date Sep 2, 2014 7:25:22 PM
 * 
 */
@Controller
@RequestMapping(value="records")
public class RecordsController extends BaseController {

	
	@Resource
	private RecordsService recordsService;
		

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView records(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		TadminUser tUser=getSessionUser(request);
		if(tUser!=null){
			mav.setViewName("records/records");
	    }
		else{
			tUser=new TadminUser();		
			mav.addObject("user", tUser);
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping(value="recordsList",method=RequestMethod.GET)
	@ResponseBody
	public String recordsList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=recordsService.loadRecordsList(dtp);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		pagingData.setSEcho(dtp.sEcho);
		
		String recordsListJson= JSON.toJSONString(pagingData);
		return recordsListJson;
	}
		
	
//	/**
//	 * <p>Description: 处理新增数据的ajax请求</p>
//	 * @Title: addRecords 
//	 * @param jsonStr
//	 * @param request
//	 * @return String
//	 * @throws
//	 */
//	@RequestMapping(value="addRecords",method=RequestMethod.POST)
//	@ResponseBody
//	public String addRecords(HttpServletRequest request,Trecords record){			
//		JSONObject respJson = new JSONObject();
//		try{
//			recordsService.createRecord(record);			
//			respJson.put("status", true);
//		}
//		catch(MyException be){
//			respJson.put("status", false);
//			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
//		}		
//		return JSON.toJSONString(respJson);
//	}
//	
//	@RequestMapping(value="editRecords",method=RequestMethod.POST)
//	@ResponseBody
//	public String updateRecords(HttpServletRequest request,Trecords record){		
//
//		JSONObject respJson = new JSONObject();
//		try{
//			recordsService.updateRecord(record);
//			respJson.put("status", true);
//		}
//		catch(MyException be){
//			respJson.put("status", false);
//			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
//		}	
//		return JSON.toJSONString(respJson);		
//	}

	@RequestMapping(value="records/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteRecords(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			recordsService.deleteRecordsByIds(idArr);
			respJson.put("status", true);
		}
		catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", getMessage(request,be.getErrorID(),be.getMessage()));
		}	
		return JSON.toJSONString(respJson);	
	}
}

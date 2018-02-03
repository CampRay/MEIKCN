/**   
 * @Title: UserController.java 
 * @Package com.uswop.action 
 * @Description: TODO
 * @author Phills Li    
 * @date Sep 2, 2014 7:25:22 PM 
 * @version V1.0   
 */
package com.nuvomed.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.commons.MyException;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.service.AdminUserService;
//import com.bps.service.InterfaceService;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @author Phills Li
 * @date Sep 2, 2014 7:25:22 PM
 * 
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
	
	
	@Autowired
	private AdminUserService adminUserService;		
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		TadminUser tUser=getSessionUser(request);
		if(tUser!=null){
			mav.setViewName("redirect:/jobs");	
		}
		else{
			tUser=new TadminUser();		
			mav.addObject("user", tUser);
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping(value="/getAmount",method=RequestMethod.GET)
	@ResponseBody
	public String systemUserAmount(HttpServletRequest request,@RequestParam(value="id",required=true) int id){
		JSONObject resp = new JSONObject();
		int amount=0;
		try{
			if(id==0){
		     	amount=adminUserService.getAdminUserAmount();
			}else if(id==1){
				
			}else{
				
			}
			resp.put("amount", amount);
			resp.put("status", true);
		}catch(MyException b){
			resp.put("status", false);
			resp.put("info",b.getMessage());
		}
		
		return JSON.toJSONString(resp);
	
	}
	
	@RequestMapping(value="/getCpuStatus",method=RequestMethod.GET)
	@ResponseBody
	public String getSystemCpuStatus(HttpServletRequest request) throws IOException{
		loadLibiray(request);
		JSONObject respJson = new JSONObject();
		Sigar sigar = new Sigar();
		try{
			CpuPerc perc = sigar.getCpuPerc();
			long cpu=Math.round(perc.getCombined()*100);
			
			respJson.put("cpu", cpu);
		}catch(MyException b){
			
			
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return JSON.toJSONString(respJson);
	}
	
	@RequestMapping(value="/getMemStatus",method=RequestMethod.GET)
	@ResponseBody
	public String getSystemMemStatus(HttpServletRequest request) throws IOException{
		loadLibiray(request);
		JSONObject respJson = new JSONObject();
		Sigar sigar = new Sigar();
		try{
			Mem mem;
			mem = sigar.getMem();
			long m=Math.round(mem.getUsedPercent());
			
			respJson.put("m", m);
		}catch(MyException b){
			
			
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return JSON.toJSONString(respJson);
	}
	
	
    public static void loadLibiray(HttpServletRequest request){
    	String path=request.getSession().getServletContext().getRealPath("/")+File.separator+"static"+File.separator+"lib"+File.separator;
    	if(System.getProperty("os.name").startsWith("Windows")){
    		if(System.getProperty("os.arch").endsWith("64")){
    			System.load(path+"sigar-amd64-winnt.dll");
    		}else{
    			System.load(path+"sigar-x86-winnt.dll");
    		}
        }
    }	
}

/**   
 * @Title: UserController.java 
 * @Package com.uswop.action 
 * @Description: TODO
 * @author Phills Li    
 * @date Sep 2, 2014 7:25:22 PM 
 * @version V1.0   
 */
package com.nuvomed.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.commons.ConvertTools;
import com.nuvomed.commons.MyException;
import com.nuvomed.commons.SecurityTools;
import com.nuvomed.commons.SystemConstants;
import com.nuvomed.dto.TadminInfo;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.model.ChangePasswordModel;
import com.nuvomed.service.AdminInfoService;
import com.nuvomed.service.AdminUserService;
@Controller
@RequestMapping(value="userprofile")
public class AdminInfoController extends BaseController {

	
	@Autowired
	private AdminInfoService adminInfoService;
	
    @Autowired
	private AdminUserService adminUserService;        
    
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView user(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		TadminInfo adminInfo=new TadminInfo();
		try{
          TadminUser adminUser = (TadminUser) request.getSession().getAttribute(SystemConstants.LOGINED);
            
             mav.setViewName("login");
	        
			String adminId=adminUser.getAdminId();
			
			adminInfo = adminInfoService.getAdminInfoById(adminId);
			
			if(adminInfo == null){
				adminInfo = new TadminInfo();
				adminInfo.setAdminId(adminId);
				adminInfo.setGender(true);
				adminInfoService.createAdminInfo(adminInfo);
			}
			if(adminInfo.getGender()==null){
				adminInfo.setGender(true);
			}
			adminInfo.setEmail(adminUser.getEmail());
	        
        }catch(MyException be){
        	
        }
		mav.addObject("adminInfo", adminInfo);
		mav.setViewName("userprofile/personal_info");
		return mav;
		
	}
	
	@RequestMapping(value="editprofile",method=RequestMethod.POST)
	@ResponseBody
	public String editAdminInfo(HttpServletRequest request,TadminInfo adminInfo){
		JSONObject respJson = new JSONObject();		
		try{
			TadminUser adminUser = (TadminUser) request.getSession().getAttribute(SystemConstants.LOGINED);
			TadminUser user = adminUserService.getTadminUsersByEmail(adminInfo.getEmail());
			 String adminId=adminUser.getAdminId();
			if(user != null && !adminUser.getAdminId().equals(user.getAdminId())){
				respJson.put("status", false);
				respJson.put("info", "Email is exists ,Please change another.");
				return JSON.toJSONString(respJson);
			}
			if(!adminUser.getEmail().equals(adminInfo.getEmail())){
				request.getSession().removeAttribute(SystemConstants.LOGINED);
				user = adminUserService.getAdminUserById(adminId);
				user.setEmail(adminInfo.getEmail());
				user.setUpdatedBy(adminId);
				user.setUpdatedTime(System.currentTimeMillis());
				adminUserService.updateAdminUser(user);
				setSessionUser(request, user);
			}		    
			adminInfo.setAdminId(adminId);
			adminInfoService.updateAdminInfo(adminInfo);			
			respJson.put("status", true);
		}catch(MyException be){
			respJson.put("status", false);
		}		
		return JSON.toJSONString(respJson);
	   }
	 
	@RequestMapping(value="changePassword",method=RequestMethod.POST)
	@ResponseBody
	public String changePassword(HttpServletRequest request,ChangePasswordModel cpMod){
		JSONObject respJson = new JSONObject();		
		try{
			TadminUser adminUser = (TadminUser) request.getSession().getAttribute(SystemConstants.LOGINED);			
			if(!SecurityTools.SHA1(cpMod.getOldpassword()).equals(adminUser.getPassword())){					
                respJson.put("status", true);
				respJson.put("olderror",true);
			}
			else{
				request.getSession().removeAttribute(SystemConstants.LOGINED);
				adminUser.setPassword(SecurityTools.SHA1(cpMod.getNewpassword()));
				adminUserService.updateAdminUserPassword(adminUser);				
				respJson.put("status", true);
			}
			
		}catch(MyException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}		
		return JSON.toJSONString(respJson);
	   }
	
	@RequestMapping(value="chageAvatar",method=RequestMethod.POST)
	public String changeAvatar(HttpServletRequest request,@RequestParam(value = "avatar", required = false) MultipartFile file) throws IOException{		
		InputStream inputStream = file.getInputStream();		
		byte [] avatar=ConvertTools.toByteArray(inputStream);		
		try{
			TadminUser adminUser = (TadminUser) request.getSession().getAttribute(SystemConstants.LOGINED);
			if(adminUser!=null){				
				String adminId=adminUser.getAdminId();
				TadminInfo adminInfo=adminInfoService.getAdminInfoById(adminId);
				if(adminInfo!=null){
					adminInfo.setAvatar(avatar);			
					adminInfoService.updateAdminInfoAvatar(adminInfo);
				}
				else{
					adminInfo=new TadminInfo();	
					adminInfo.setAdminId(adminUser.getAdminId());
					adminInfo.setAvatar(avatar);
					adminInfoService.createAdminInfo(adminInfo);
				}
			}
		}catch(MyException be){
			
		}		
		return "redirect:/userprofile"; 
	   }
      
	@RequestMapping(value="getAvatar",method=RequestMethod.GET)
	public void getAvatar(HttpServletRequest request,HttpServletResponse response){
		try{
			TadminUser adminUser = (TadminUser) request.getSession().getAttribute(SystemConstants.LOGINED);
			TadminInfo adminInfo = new TadminInfo();
			String adminId=adminUser.getAdminId();
			adminInfo=adminInfoService.getAdminInfoById(adminId);
			//设置页面不缓存
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			ByteArrayInputStream bin;
			if(adminInfo==null||adminInfo.getAvatar() == null){
				File file = new File(request.getSession().getServletContext().getRealPath("/")+File.separator+"static"+File.separator+"images"+File.separator+"profile.jpg");
			    FileImageInputStream inputStream = new FileImageInputStream(file);
				byte [] avatar=new byte[1048576];
				inputStream.read(avatar);
				inputStream.close();
				bin = new ByteArrayInputStream(avatar);
				
			 }else{
				bin = new ByteArrayInputStream(adminInfo.getAvatar()); 
			 }
			BufferedImage buffImage=ImageIO.read(bin);
		    ImageIO.write(buffImage, "JPEG", response.getOutputStream());
			 
		}catch(MyException b){
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
}

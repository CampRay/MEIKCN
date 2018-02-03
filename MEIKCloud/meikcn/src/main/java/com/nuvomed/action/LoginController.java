/**   
* @Title: LoginController.java 
* @Package com.uswop.action 
*
* @Description: TODO
* 
* @date Sep 10, 2014 3:06:32 PM
* @version V1.0   
*/ 
package com.nuvomed.action;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nuvomed.commons.EMailTool;
import com.nuvomed.commons.SecurityTools;
import com.nuvomed.commons.SystemConfig;
import com.nuvomed.commons.SystemConstants;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.TemaiMessage;
import com.nuvomed.service.AdminUserService;

/** 
 * @ClassName: LoginController 
 * @Description: 
 * @author Phills Li
 * @date Sep 10, 2014 3:06:32 PM 
 *  
 */
@Controller
public class LoginController extends BaseController {	
	@Autowired
	private AdminUserService adminUserService;		
	   
	/** 
	 * <p>Open the login page</p>
	 * @Title: login 
	 * @return String
	 * @throws 
	 */     
    @RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav=new ModelAndView();
		TadminUser tUser=new TadminUser();		
		mav.addObject("user", tUser);
		mav.setViewName("login");
		return mav;
	}
		
	
	/** 
	 * <p>User Login</p>
	 * @Title: login 
	 * @param request
	 * @param user
	 * @return ModelAndView
	 * @throws 
	 */ 
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,TadminUser user){				
		TadminUser tUser=(TadminUser)adminUserService.getAdminUserByIdOrEmail(user.getAdminId());
		ModelAndView mav=new ModelAndView();
		Long time = (Long) request.getSession().getAttribute(SystemConstants.LOGIN_STATUS);
		if(time != null && System.currentTimeMillis()-time<60000*Integer.parseInt(SystemConfig.Admin_Setting_Map.get(SystemConstants.LOGIN_ERROR_LOCK))){
			    mav.addObject(ERROR_MSG_KEY, "The password is wrong too many times account is locked for "+Integer.parseInt(SystemConfig.Admin_Setting_Map.get(SystemConstants.LOGIN_ERROR_LOCK))+" minutes.");
				if(tUser != null){
				  mav.addObject("user", tUser);
				}else{
					mav.addObject("user", new TadminUser());
				}								
		}else if(tUser==null){
			mav.addObject(ERROR_MSG_KEY, " Login failed (Username/Password refused) .");
			mav.addObject("user", new TadminUser());
			saveLoginErrorTims(request);						
		}else if(!tUser.getStatus() && !"admin".equals(tUser.getAdminId())){
			mav.addObject(ERROR_MSG_KEY, " Login failed (Username/Password refused) .");
			mav.addObject("user", new TadminUser());
			saveLoginErrorTims(request);						
		}else if(!tUser.getAdminRole().getStatus() && !"admin".equals(tUser.getAdminId())){
			mav.addObject(ERROR_MSG_KEY, " Login failed (Username/Password refused) .");
			mav.addObject("user", new TadminUser());
			saveLoginErrorTims(request);			
		}
		else if(!SecurityTools.SHA1(user.getPassword()).equalsIgnoreCase(tUser.getPassword())){
			mav.addObject(ERROR_MSG_KEY, " Login failed (Username/Password refused). ");
			mav.addObject("user", tUser);
			saveLoginErrorTims(request);						
		}else{
			setSessionUser(request, tUser);
			String toUrl=(String)request.getSession().getAttribute(LOGIN_TO_URL);
			request.getSession().removeAttribute(LOGIN_TO_URL);
			request.getSession().removeAttribute(SystemConstants.LOGIN_ERROR);
			request.getSession().removeAttribute(SystemConstants.LOGIN_STATUS);
			
//			request.getSession().removeAttribute("locale");			
//			Locale locale=LocaleContextHolder.getLocale();
//			request.getSession().setAttribute("locale", locale);
			
			if(StringUtils.isEmpty(toUrl)){
				if(tUser.getAdminRole().getRoleId()!=5){
					toUrl="/jobs";
				}
				else{					
					toUrl="/regSearch";
				}
			}
			
			mav.setViewName("redirect:"+toUrl);						
		}		
		return mav;
	}
	
	@RequestMapping(value="/ResetPassword",method=RequestMethod.POST)
	public ModelAndView resetPassword(HttpServletRequest request ,String email){
		ModelAndView mav = new ModelAndView();
		
		TadminUser adminUser = adminUserService.getTadminUsersByEmail(email);
		if(adminUser == null){
			 adminUser = new TadminUser();
			 
			 mav.addObject(ERROR_MSG_KEY,"email not exist.");
		}else{					   
			String random = UUID.randomUUID().toString().trim().replace("-","").substring(0,6);
			adminUser.setPassword(SecurityTools.SHA1(random));
			adminUser.setUpdatedBy(adminUser.getAdminId());
			adminUser.setUpdatedTime(System.currentTimeMillis());
			TemaiMessage message=new TemaiMessage();
			message.setTo(email);
			StringBuffer sb=new StringBuffer();
			sb.append("<p>"+adminUser.getAdminId()+":</p><br/>");
			sb.append("<p>您在数据服务中心的帐号的密码已经被重新设置，您可以随时使用新的密码登入数据服务中心网站，然后修改您的密码。</p><p>新的密码是："+random+"</p>");	
			sb.append("<br/><p>数据服务中心网站访问链接为："+SystemConfig.Admin_Setting_Map.get("Service_URL")+"</p>");
			message.setText(sb.toString());
			message.setSubject("密码重置");
			EMailTool.send(message,true);
			adminUserService.updateAdminUserPassword(adminUser);
			mav.addObject(ERROR_MSG_KEY,"password reset success.");			
		}
		mav.addObject("user",adminUser);
		mav.setViewName("login");				
		return mav;
	}
	
	/** 
	 * <p></p>
	 * @Title: logout 
	 * @param session
	 * @return String
	 * @throws 
	 */ 
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){		
		if((TadminUser)session.getAttribute(SystemConstants.LOGINED)!=null){		
		}
		session.removeAttribute(SystemConstants.LOGINED);			
		return "forward:login";
	}
}

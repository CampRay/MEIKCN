package com.nuvomed.api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.commons.ConvertTools;
import com.nuvomed.commons.EMailTool;
import com.nuvomed.commons.SecurityTools;
import com.nuvomed.commons.SystemConfig;
import com.nuvomed.core.MultiThreadHandler;
import com.nuvomed.dto.TadminJob;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.TemaiMessage;
import com.nuvomed.dto.Tlicense;
import com.nuvomed.dto.Trecords;
import com.nuvomed.dto.Tuser;
import com.nuvomed.dto.TuserData;
import com.nuvomed.dto.TuserInfo;
import com.nuvomed.service.AdminJobService;
import com.nuvomed.service.AdminUserService;
import com.nuvomed.service.GroupUserService;
import com.nuvomed.service.LicenseService;
import com.nuvomed.service.RecordsService;
import com.nuvomed.service.UserDataService;
import com.nuvomed.service.UserInfoService;


@Controller
@RequestMapping("/api")
public class ClientAPI {
	 
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserDataService userDataService;
	@Autowired
	private AdminJobService adminJobService;	
	@Autowired
	private LicenseService licenseService;
	@Autowired
	private RecordsService recordsService;

	@Autowired
	private GroupUserService groupUserService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	
	
	/**
	 * 激活MEIK Screen程序的lisense
	 * @param response
	 * @param jsonStr
	 * @return String
	 */
	@RequestMapping(value="/active",method=RequestMethod.POST)
	@ResponseBody
	public String active(@RequestBody String jsonStr){					
		JSONObject respJson = new JSONObject();	
		if (jsonStr == null || jsonStr.isEmpty()) {
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_1");
			return JSON.toJSONString(respJson);
		}
		
		JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
		if(jsonObj!=null){
			String license = jsonObj.getString("license");
			String cpuid = jsonObj.getString("cpuid");	
			//String deviceId = jsonObj.getString("deviceId");				
			if (license==null||license.isEmpty()) {
				respJson.put("status", 0);
				//respJson.put("info", "The license is required.");
				respJson.put("info", "API_MSG_1");
				return JSON.toJSONString(respJson);
			}
			
			try{
				Tlicense tLicense=licenseService.getLicenseById(license);
				if(tLicense==null){
					respJson.put("status", 0);
					//respJson.put("info", "The license is invalid.");
					respJson.put("info", "API_MSG_2");
				}
				else if(tLicense.getType()!=0){
					respJson.put("status", 0);
					//respJson.put("info", "The license is disabled.");
					respJson.put("info", "API_MSG_2");
				}
				else if(!tLicense.isStatus()){
					respJson.put("status", 0);
					//respJson.put("info", "The license is disabled.");
					respJson.put("info", "API_MSG_3");
				}
				else if(tLicense.getDeadline()!=null&&tLicense.getDeadline()<=System.currentTimeMillis()){
					respJson.put("status", 0);
					//respJson.put("info", "The license have expired.");
					respJson.put("info", "API_MSG_4");
				}
				else{
					tLicense.setCpuId(cpuid);
					//tLicense.setDeviceId(deviceId);
					tLicense.setActiveTime(System.currentTimeMillis());
					licenseService.updateLicense(tLicense);
					respJson.put("status", 1);
				}
			}
			catch(Exception e){
				respJson.put("status", 0);
				//respJson.put("info", "Activation server failure, failed to activate the license, please try again later.");
				respJson.put("info", "API_MSG_5");
			}
		}
		else{
			respJson.put("status", 0);
			//respJson.put("info", "The license is required.");
			respJson.put("info", "API_MSG_1");
			return JSON.toJSONString(respJson);
		}
			
		
		return JSON.toJSONString(respJson);
	}
	
	/**
	 * 检测MEIK Screen程序lisense的有效性
	 * @param response
	 * @param jsonStr
	 * @return String
	 */
	@RequestMapping(value="/check",method=RequestMethod.POST)
	@ResponseBody
	public String check(@RequestBody String jsonStr){					
		JSONObject respJson = new JSONObject();	
		if (jsonStr == null || jsonStr.isEmpty()) {
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_1");
			return JSON.toJSONString(respJson);
		}
		
		JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
		String license = jsonObj.getString("license");
		String cpuid = jsonObj.getString("cpuid");			
		if (license==null||license.isEmpty()) {
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_1");
			return JSON.toJSONString(respJson);
		}
			
		Tlicense tLicense=licenseService.getLicenseById(license);
		if(tLicense==null){
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_2");
		}
		else if(tLicense.getType()!=0){
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_2");
		}
		else if(!tLicense.isStatus()){
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_3");
		}
		else if(tLicense.getDeadline()!=null&&tLicense.getDeadline()<=System.currentTimeMillis()){
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_4");
		}
		else if(tLicense.getCpuId().equalsIgnoreCase(cpuid)){
			respJson.put("status", 1);
		}
		else{
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_5");
		}
				
		return JSON.toJSONString(respJson);
	}
	
	/**
	 * 激活MEIK Report程序的lisense
	 * @param response
	 * @param jsonStr
	 * @return String
	 */
	@RequestMapping(value="/activeReport",method=RequestMethod.POST)
	@ResponseBody
	public String activeReport(@RequestBody String jsonStr){					
		JSONObject respJson = new JSONObject();	
		if (jsonStr == null || jsonStr.isEmpty()) {
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_1");
			return JSON.toJSONString(respJson);
		}
		
		JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
		if(jsonObj!=null){
			String license = jsonObj.getString("license");
			String cpuid = jsonObj.getString("cpuid");	
			//String deviceId = jsonObj.getString("deviceId");				
			if (license==null||license.isEmpty()) {
				respJson.put("status", 0);
				//respJson.put("info", "The license is required.");
				respJson.put("info", "API_MSG_1");
				return JSON.toJSONString(respJson);
			}
			
			try{
				Tlicense tLicense=licenseService.getLicenseById(license);
				if(tLicense==null){
					respJson.put("status", 0);
					//respJson.put("info", "The license is invalid.");
					respJson.put("info", "API_MSG_2");
				}
				else if(tLicense.getType()!=1){
					respJson.put("status", 0);
					//respJson.put("info", "The license is disabled.");
					respJson.put("info", "API_MSG_2");
				}
				else if(!tLicense.isStatus()){
					respJson.put("status", 0);
					//respJson.put("info", "The license is disabled.");
					respJson.put("info", "API_MSG_3");
				}
				else if(tLicense.getDeadline()!=null&&tLicense.getDeadline()<=System.currentTimeMillis()){
					respJson.put("status", 0);
					//respJson.put("info", "The license have expired.");
					respJson.put("info", "API_MSG_4");
				}
				else{
					tLicense.setCpuId(cpuid);
					//tLicense.setDeviceId(deviceId);
					tLicense.setActiveTime(System.currentTimeMillis());
					licenseService.updateLicense(tLicense);
					respJson.put("status", 1);
				}
			}
			catch(Exception e){
				respJson.put("status", 0);
				//respJson.put("info", "Activation server failure, failed to activate the license, please try again later.");
				respJson.put("info", "API_MSG_5");
			}
		}
		else{
			respJson.put("status", 0);
			//respJson.put("info", "The license is required.");
			respJson.put("info", "API_MSG_1");
			return JSON.toJSONString(respJson);
		}
			
		
		return JSON.toJSONString(respJson);
	}
	
	/**
	 * 检测MEIK Report程序lisense的有效性
	 * @param response
	 * @param jsonStr
	 * @return String
	 */
	@RequestMapping(value="/checkReport",method=RequestMethod.POST)
	@ResponseBody
	public String checkReport(@RequestBody String jsonStr){					
		JSONObject respJson = new JSONObject();	
		if (jsonStr == null || jsonStr.isEmpty()) {
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_1");
			return JSON.toJSONString(respJson);
		}
		
		JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
		String license = jsonObj.getString("license");
		String cpuid = jsonObj.getString("cpuid");			
		if (license==null||license.isEmpty()) {
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_1");
			return JSON.toJSONString(respJson);
		}
			
		Tlicense tLicense=licenseService.getLicenseById(license);
		if(tLicense==null){
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_2");
		}
		else if(tLicense.getType()!=1){
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_2");
		}
		else if(!tLicense.isStatus()){
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_3");
		}
		else if(tLicense.getDeadline()!=null&&tLicense.getDeadline()<=System.currentTimeMillis()){
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_4");
		}
		else if(tLicense.getCpuId().equalsIgnoreCase(cpuid)){
			respJson.put("status", 1);
		}
		else{
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_5");
		}
				
		return JSON.toJSONString(respJson);
	}
		
	
	/**
	 * User Login
	 * @param response
	 * @param jsonStr
	 * @return String
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody String jsonStr){			
		JSONObject jsonObj = null;
		JSONObject respJson = new JSONObject();		
		if (jsonStr == null || jsonStr.isEmpty()) {
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_6");
			return JSON.toJSONString(respJson);
		}
		
		jsonObj = (JSONObject) JSON.parse(jsonStr);
		String username = jsonObj.getString("username");
		String password = jsonObj.getString("password");		
		if (username==null||username.isEmpty()) {
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_7");
			return JSON.toJSONString(respJson);
		}
				
		
		TadminUser adminUser=(TadminUser)adminUserService.getAdminUserByIdOrEmail(username);
		if(adminUser==null||!adminUser.getStatus()||!SecurityTools.SHA1(password).equalsIgnoreCase(adminUser.getPassword())){		
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_8");			
		}else{
			String token=UUID.randomUUID().toString();
			adminUser.setToken(token);
			adminUserService.updateAdminUser(adminUser);
			respJson.put("status", 1);
			JSONObject dataJson = new JSONObject();					
			dataJson.put("token",token);
			respJson.put("data", dataJson);
		}		
		return JSON.toJSONString(respJson);
	}
	
	
	/**
	 * User Login
	 * @param response
	 * @param jsonStr
	 * @return String
	 */
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletResponse response,@RequestHeader("Authorization") String token){	
		TadminUser admin=adminUserService.getTadminUsersByToken(token);
		admin.setToken(null);
		adminUserService.updateAdminUser(admin);
		JSONObject respJson = new JSONObject();			
		respJson.put("status", 1);			
		return JSON.toJSONString(respJson);
						
	}	
	
	/**
	 * MEIK Screen上传用户检查数据接口
	 * @param request
	 * @param token
	 * @param jsonStr
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/sendData",method=RequestMethod.POST)
	@ResponseBody
	public String sendData(HttpServletRequest request,@RequestHeader("Authorization") String token,String jsonStr,@RequestParam(value = "file", required = false) MultipartFile file){
		TadminUser admin=adminUserService.getTadminUsersByToken(token);
		JSONObject respJson = new JSONObject();	
		if (admin== null) {
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_9");
			return JSON.toJSONString(respJson);
		}
		
		JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
		if (jsonObj.getString("code")== null ||jsonObj.getString("code").isEmpty()) {
			respJson.put("status", 0);			
			return JSON.toJSONString(respJson);
		}
		TadminJob adminJob=adminJobService.getAdminJobByCode(jsonObj.getString("code"));
		if(adminJob!=null){					
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_10");
			return JSON.toJSONString(respJson);
		}									
		
		try{
										
			Tuser user=new Tuser();
			TuserData userData=new TuserData();			
			
//			String clientNumber=jsonObj.getString("clientnumber");
			user.setCid(jsonObj.getString("cid"));
			if(user.getCid()!=null&&!user.getCid().isEmpty()){
				TadminUser newAccount=adminUserService.getAdminUserById(user.getCid());
				user.setAdminUser(newAccount);
			}
			user.setCode(jsonObj.getString("code"));		
			user.setFirstName(jsonObj.getString("firstname"));
			user.setLastName(jsonObj.getString("lastname"));		
			user.setOtherName(jsonObj.getString("othername"));
			user.setBirthday(jsonObj.getString("birthday"));
			user.setMobile(jsonObj.getString("mobile"));	
			user.setEmail(jsonObj.getString("email"));
			user.setFree(jsonObj.getBoolean("free"));						
			user.setLocation(jsonObj.getString("venue"));
			user.setResult(jsonObj.getInteger("result"));
			user.setGender(true);
			user.setCreatedBy(admin.getAdminId());
									
			TuserInfo userInfo=new TuserInfo();
	 		userInfo.setFirstName(user.getFirstName());
	 		userInfo.setLastName(user.getLastName());
	 		userInfo.setOtherName(user.getOtherName());
	 		userInfo.setBirthday(user.getBirthday());
	 		userInfo.setMobile(user.getMobile());
	 		userInfo.setEmail(user.getEmail());
	 		userInfo.setCreatedTime(System.currentTimeMillis());	 		
	 		userInfo.setAdminUser(user.getAdminUser());
			
			InputStream inputStream = file.getInputStream();
			byte [] fileBytes=ConvertTools.toByteArray(inputStream);	
			userData.setFileName(file.getOriginalFilename());
			userData.setStream(fileBytes);				
			userData.setDataType(1);
			userData.setUserId(user.getUserId());										
			
			adminJob=adminJobService.createScreenData(user,userInfo, userData,admin.getAdminId());
			//则发送通知电邮	
			TadminUser systemDoctor=adminJob.getAdminUser();
			if(systemDoctor.getAdminInfo()==null||systemDoctor.getAdminInfo().getNotify()){
				try{
					//发送通知电邮					
					TemaiMessage message=new TemaiMessage();
					message.setTo(systemDoctor.getEmail());
					StringBuffer sb=new StringBuffer();
					sb.append("<p>管理员-");
					sb.append(systemDoctor.getAdminId());
					sb.append(":</p><br/>");
					sb.append("<p>检测编号为 "+adminJob.getUser().getCode()+"的客户扫描数据已经上传到数据服务中心, 您可以直接打开MEIK Report客户端工具，使用您的报告管理员帐号登录，点击接收数据按钮就可以从数据服务中心接收此检测数据，然后您可以填写检查报告并上传检查报告到数据服务中心，");
					sb.append("或者是使用您的报告管理员帐号登录到数据服务中心网站，然后为此检测数据指定一名医生，由该医生来完成最终的检测报告。</p>");
					sb.append("<br/><p>数据服务中心网站访问链接为："+SystemConfig.Admin_Setting_Map.get("Service_URL")+"</p>");
					message.setText(sb.toString());
					message.setSubject("通知-收到编号为"+user.getCode()+"MEIK检测数据");
					EMailTool.send(message,true);
				}
				catch(Exception e){}
			}	
			respJson.put("status", 1);
			if(user.getResult()!=null&&user.getResult()==-1){
				taskExecutor.execute(new MultiThreadHandler(user));
			}
																					
		}catch (Exception e) {		
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_11");
			respJson.put("msg", e.getMessage());
			return JSON.toJSONString(respJson);
		}
		return JSON.toJSONString(respJson);
		
	}
						
	
	
	/**
	 * 医生上传报告数据接口
	 * @param request
	 * @param token
	 * @param jsonStr
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/sendReport",method=RequestMethod.POST)
	@ResponseBody
	public String sendReport(HttpServletRequest request,@RequestHeader("Authorization") String token,String jsonStr,@RequestParam(value = "file", required = false) MultipartFile file){		
		TadminUser admin=adminUserService.getTadminUsersByToken(token);		
		JSONObject respJson = new JSONObject();	
		if (admin== null) {
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_9");
			return JSON.toJSONString(respJson);
		}
		
		JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
		String code=jsonObj.getString("code");
		//String email=jsonObj.getString("email");
		String doctor=admin.getAdminId();		
		//获取对应的任务记录
		TadminJob adminJob=adminJobService.loadDownloadedJobByUserCode(doctor, code);
		//判断是否已经上传过数据
		if(adminJob==null){
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_12");
			return JSON.toJSONString(respJson);
		}		
		
		Tuser user=adminJob.getUser();				
		//判断是否是系统医生		
		int dataType=2;		
		
		adminJob.setType(7);	
		//设置医生完成报告时间
		adminJob.setDoneTime(System.currentTimeMillis());
		//设置报告完成时间
		adminJob.setCloseTime(System.currentTimeMillis());						
		try{					
			//查询操作员所在组的系统医生（报表管理员）帐号		
			List<TadminUser> systemDocotrList=groupUserService.getGroupManagerIdsByUser(adminJob.getCreatedBy());
			TadminUser systemDoctor=null;
			if(systemDocotrList.size()>0){
				systemDoctor=systemDocotrList.get(0);						
			}
				
			//发送通知电邮			
			if(systemDoctor.getAdminInfo()==null||systemDoctor.getAdminInfo().getNotify()){					
				TemaiMessage message=new TemaiMessage();
				message.setTo(systemDoctor.getEmail());
				StringBuffer sb=new StringBuffer();
				sb.append("<p>管理员-");
				sb.append(systemDoctor.getAdminId());
				sb.append(":</p><br/>");
				sb.append("<p>检测编号为 "+user.getCode()+"的诊断报告已经完成, 您可以登录到数据服务中心网站下载报告数据.</p>");
				sb.append("<br/><p>数据服务中心网站访问链接为："+SystemConfig.Admin_Setting_Map.get("Service_URL")+"</p>");
				message.setText(sb.toString());
				message.setSubject("通知-编号为"+user.getCode()+"MEIK检测报告已完成");
				EMailTool.send(message,true);
			}
		}
		catch(Exception e){}
		
		
		//保存上传数据
		//查询之前是否有已经有上传数据
		List<TuserData> userDataList=userDataService.loadUserReportDataList(user.getUserId());			
		if(userDataList==null||userDataList.size()==0){
			//保存上传数据
			TuserData userData=new TuserData();
			InputStream inputStream;
			try {
				inputStream = file.getInputStream();
				byte [] fileBytes=ConvertTools.toByteArray(inputStream);
						
				userData.setFileName(file.getOriginalFilename());
				userData.setStream(fileBytes);	
				userData.setUserId(user.getUserId());
				userData.setDataType(dataType);
				userDataService.createUserData(userData);
			} catch (Exception e) {			
				respJson.put("status", 0);
				respJson.put("info", "API_MSG_13");
				return JSON.toJSONString(respJson);
			}	
		}
		else{
			//保存上传数据
			TuserData userData=userDataList.get(userDataList.size()-1);
			InputStream inputStream;
			try {
				inputStream = file.getInputStream();
				byte [] fileBytes=ConvertTools.toByteArray(inputStream);
						
				userData.setFileName(file.getOriginalFilename());
				userData.setStream(fileBytes);	
				userData.setUserId(user.getUserId());
				userData.setDataType(dataType);
				userDataService.updateUserData(userData);
			} catch (Exception e) {			
				respJson.put("status", 0);
				respJson.put("info", "API_MSG_13");
				return JSON.toJSONString(respJson);
			}	
		}			
		adminJobService.updateAdminJob(adminJob);
		
		
		
		respJson.put("status", 1);	
		return JSON.toJSONString(respJson);
	}
	
	/**
	 * 医生上传PDF报告数据接口
	 * @param request
	 * @param token
	 * @param jsonStr
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/sendPDF",method=RequestMethod.POST)
	@ResponseBody
	public String sendPDFFile(HttpServletRequest request,@RequestHeader("Authorization") String token,String jsonStr,@RequestParam(value = "file", required = false) MultipartFile file){
		String doctorId=SystemConfig.Admin_Setting_Map.get("System_Doctor_Id");
		TadminUser admin=adminUserService.getTadminUsersByToken(token);
		JSONObject respJson = new JSONObject();	
		if (admin== null) {
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_9");
			return JSON.toJSONString(respJson);
		}
		
		JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
		String doctor=null;
		if(!admin.getAdminId().equals(doctorId)){
			doctor=admin.getAdminId();
		}					
		TadminJob adminJob=adminJobService.loadDownloadedJobByUserCode(doctor, jsonObj.getString("code"));
		//判断是否已经上传过数据
		if(adminJob==null){
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_12");
			return JSON.toJSONString(respJson);
		}
		
		Tuser user=adminJob.getUser();
		List<TuserData> userDataList=userDataService.loadUserReportPdfList(user.getUserId());
		if(userDataList==null||userDataList.size()==0){
			//保存上传数据
			TuserData userData=new TuserData();
			InputStream inputStream;
			try {
				inputStream = file.getInputStream();
				byte [] fileBytes=ConvertTools.toByteArray(inputStream);
						
				userData.setFileName(file.getOriginalFilename());
				userData.setStream(fileBytes);	
				userData.setUserId(user.getUserId());
				userData.setDataType(4);
				userDataService.createUserData(userData);
			} catch (Exception e) {			
				respJson.put("status", 0);
				respJson.put("info", "API_MSG_13");
				return JSON.toJSONString(respJson);
			}	
		}
		else{
			//保存上传数据
			TuserData userData=userDataList.get(userDataList.size()-1);
			InputStream inputStream;
			try {
				inputStream = file.getInputStream();
				byte [] fileBytes=ConvertTools.toByteArray(inputStream);
						
				userData.setFileName(file.getOriginalFilename());
				userData.setStream(fileBytes);	
				userData.setUserId(user.getUserId());
				userData.setDataType(4);
				userDataService.updateUserData(userData);
			} catch (Exception e) {			
				respJson.put("status", 0);
				respJson.put("info", "API_MSG_13");
				return JSON.toJSONString(respJson);
			}	
		}
		respJson.put("status", 1);	
		return JSON.toJSONString(respJson);
	}
	
	
	
	/**
	 * 获取所有检查数据列表
	 * @param response
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="getScreenDataList",method=RequestMethod.GET)
	@ResponseBody
	public String getScreenDataList(@RequestHeader("Authorization") String token,@RequestBody String jsonStr){
		TadminUser admin=adminUserService.getTadminUsersByToken(token);
		JSONObject respJson = new JSONObject();	
		if (admin== null) {
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_9");
			return JSON.toJSONString(respJson);
		}
		
		JSONObject dataJson = new JSONObject();	
		JSONArray jobArr=new JSONArray();	
		List<TadminJob> jobList=adminJobService.loadDataAssignedJobList(admin.getAdminId());
		if(jobList.size()>0){
			for (TadminJob doctorJob : jobList) {	
				JSONObject jobJson = new JSONObject();	
				Tuser user=doctorJob.getUser();
				jobJson.put("code", user.getCode());
				jobJson.put("firstname", user.getFirstName());
				jobJson.put("lastname", user.getLastName());
				jobJson.put("othername", user.getOtherName());
				jobJson.put("jobid", doctorJob.getJobId());
				
				JSONArray fileArr=new JSONArray();
				//这里因为一次加载太多数据文件到内存，出错错误，所以这里需要优化一下，不要把数据流也加载进来
				List<TuserData> userDataList=userDataService.loadUserScreenDataList(user.getUserId());
				for (TuserData tuserData : userDataList) {
					JSONObject jsonObj = new JSONObject();								
					jsonObj.put("fileid", tuserData.getDataId());
					jsonObj.put("filename", tuserData.getFileName());
					fileArr.add(jsonObj);
				}
				jobJson.put("files", fileArr);
				jobArr.add(jobJson);
			}
			dataJson.put("jobs", jobArr);
			respJson.put("data", dataJson);
			respJson.put("status", 1);
		}
		else{
			respJson.put("status", 0);
			respJson.put("info", "API_MSG_14");
		}
		
		return JSON.toJSONString(respJson);
	}
	
	
	/**
	 * 获取所有报告结果文件列表
	 * @param response
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/getReportDataList",method=RequestMethod.GET)
	@ResponseBody
	public String getReportDataList(@RequestHeader("Authorization") String token,@RequestBody String jsonStr){
		TadminUser admin=adminUserService.getTadminUsersByToken(token);
		JSONObject respJson = new JSONObject();	
		if (admin== null) {
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_9");
			return JSON.toJSONString(respJson);
		}
		
		JSONObject dataJson = new JSONObject();	
		JSONArray jobArr=new JSONArray();
		String doctorId=SystemConfig.Admin_Setting_Map.get("System_Doctor_Id");
		
		List<TadminJob> jobList=null;
		//如果是系統醫生或组管理员
		if(admin.getAdminId().equals(doctorId)||admin.getAdminRole().getRoleId()==4){
			jobList=adminJobService.loadAdminJobList(admin.getAdminId(),5);					
		}
		else{				
			respJson.put("status", 0);
			respJson.put("info", "Please logon with system doctor or group manager account.");
			return JSON.toJSONString(respJson);
			
		}
		//把要下载的数据列表封装到Json中
		if(jobList!=null&&jobList.size()>0){
			for (TadminJob doctorJob : jobList) {	
				JSONObject jobJson = new JSONObject();	
				Tuser user=doctorJob.getUser();
				jobJson.put("code", user.getCode());
				jobJson.put("firstname", user.getFirstName());
				jobJson.put("lastname", user.getLastName());
				jobJson.put("othername", user.getOtherName());
				jobJson.put("jobid", doctorJob.getJobId());
				
				JSONArray fileArr=new JSONArray();
				//这里因为一次加载太多数据文件到内存，出错错误，所以这里需要优化一下，不要把数据流也加载进来
				List<TuserData> userDataList=userDataService.loadUserReportDataList(user.getUserId());
				for (TuserData tuserData : userDataList) {
					JSONObject jsonObj = new JSONObject();								
					jsonObj.put("fileid", tuserData.getDataId());
					jsonObj.put("filename", tuserData.getFileName());
					fileArr.add(jsonObj);
				}
				jobJson.put("files", fileArr);
				jobArr.add(jobJson);
			}
			dataJson.put("jobs", jobArr);
			respJson.put("data", dataJson);
			respJson.put("status", 1);
		}
		else{
			respJson.put("status", 0);	
			respJson.put("info", "No new report data on servier.");
		}
		
		return JSON.toJSONString(respJson);
	}
	
	
	
	/**
	 * download data
	 * @param response
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/downloadData",method=RequestMethod.POST)	
	public String downloadData(HttpServletResponse response,@RequestHeader("Authorization") String token,@RequestBody String jsonStr){	
		TadminUser admin=adminUserService.getTadminUsersByToken(token);		
		if (admin!= null&&jsonStr != null && !jsonStr.isEmpty()) {				
			JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
			int fileid = jsonObj.getInteger("fileid");		
			if (fileid>0) {				
				try{
					TuserData userData=userDataService.getUserDataById(fileid);
					if(userData!=null){
						String fileName=URLEncoder.encode(userData.getFileName(),"UTF-8");
						
						response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
						response.addHeader("Content-Length", ""+userData.getStream().length);
						response.setContentType("application/octet-stream;charset=UTF-8");
						OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
						outputStream.write(userData.getStream());  
						outputStream.flush();  	
						outputStream.close();
						
						
//						HttpHeaders headers=new HttpHeaders();
//						headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//						headers.setContentDispositionFormData("attachment", fileName);
//						headers.setContentLength(userData.getStream().length);
//						return new ResponseEntity<byte[]>(userData.getStream(),headers,HttpStatus.OK);
					}
				}catch (Exception e) {
					
		        }				
			}							
		}		
							
		return null;
					
	}
	
	/**
	 * 医生获取工作数据成功后，调用此方法修改工作任务状态
	 * @param response
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/closeJobs",method=RequestMethod.POST)
	@ResponseBody
	public String closeJobs(HttpServletResponse response,@RequestHeader("Authorization") String token,@RequestBody String jsonStr){	
		TadminUser admin=adminUserService.getTadminUsersByToken(token);
		JSONObject respJson = new JSONObject();	
		if (admin== null) {
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_9");
			return JSON.toJSONString(respJson);
		}
		
		JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
		String jobIdsStr = jsonObj.getString("jobids");	
		String[] jobIdArr=new String[]{};
		if(jobIdsStr!=null){
			jobIdArr=jobIdsStr.split(",");
		}
		
		for (String jobId : jobIdArr) {
			TadminJob adminJob=adminJobService.getAdminJobById(Integer.parseInt(jobId));
			//如果医生下载数据成功，则修改状态类型为3
			if(adminJob.getType()==2){
				adminJob.setType(3);
				adminJob.setReportTime(System.currentTimeMillis());
				adminJobService.updateAdminJob(adminJob);	
			}
			else if(adminJob.getType()==5){
				adminJob.setType(6);				
				adminJobService.updateAdminJob(adminJob);	
			}										
					
		}
		respJson.put("status", 1);			
		return JSON.toJSONString(respJson);
					
	}	
	
	/**
	 * 获取最新的MEIK Screen安装程式版本号
	 * @param response
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/getMEIKScreenVersion",method=RequestMethod.GET)
	@ResponseBody
	public String getMEIKScreenVersion(HttpServletRequest request){		
		JSONObject respJson = new JSONObject();			
		respJson.put("status", 0);			
		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		File folder=new File(realPath,File.separator+"download"+File.separator+"MEIKScreen");	
		if(folder.exists()){			
			File[] fileList = folder.listFiles();	
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					list.add(fileList[i].getName());
				}			
			}
			Collections.reverse(list);			
			//Tversion version=versionService.loadLatestVersion(1);			
			if(list.size()>0){			
				respJson.put("data", list.get(0));
				respJson.put("status", 1);
			}		
		}
		
		return JSON.toJSONString(respJson);
	}
	
	/**
	 * 获取最新的MEIK Screen安装程式版本号
	 * @param response
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/getMEIKReportVersion",method=RequestMethod.GET)
	@ResponseBody
	public String getMEIKReportVersion(HttpServletRequest request){		
		JSONObject respJson = new JSONObject();			
		respJson.put("status", 0);			
		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		File folder=new File(realPath,File.separator+"download"+File.separator+"MEIKReport");	
		if(folder.exists()){
			File[] fileList = folder.listFiles();	
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					list.add(fileList[i].getName());
				}			
			}
			Collections.reverse(list);			
			if(list.size()>0){			
				respJson.put("data", list.get(0));
				respJson.put("status", 1);
			}		
		}
		
		return JSON.toJSONString(respJson);
	}
	
	/**
	 * 获取最新的MEIK Screen安装程式版本号
	 * @param response
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/getMEIKToolVersion",method=RequestMethod.GET)
	@ResponseBody
	public String getMEIKToolVersion(HttpServletRequest request){		
		JSONObject respJson = new JSONObject();			
		respJson.put("status", 0);			
		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		File folder=new File(realPath,File.separator+"download"+File.separator+"MEIKTool");	
		if(folder.exists()){
			File[] fileList = folder.listFiles();	
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					list.add(fileList[i].getName());
				}			
			}
			Collections.reverse(list);			
			if(list.size()>0){			
				respJson.put("data", list.get(0));
				respJson.put("status", 1);
			}		
		}
		
		return JSON.toJSONString(respJson);
	}

	/**
	 * 下載最新版本的MEIK程序
	 * download meik screen
	 * @param response
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/downloadMEIKScreen")	
	public String downloadMEIKScreen(HttpServletRequest request,HttpServletResponse response){	
		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		File folder=new File(realPath,File.separator+"download"+File.separator+"MEIKScreen");	
		if(folder.exists()){
			File[] fileList = folder.listFiles();	
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					list.add(fileList[i].getName());
				}			
			}
			Collections.reverse(list);						
			//Tversion version=versionService.loadLatestVersion(1);			
			if(list.size()>0){	
				InputStream ins=null;
				try{	
					
					String fileName=URLEncoder.encode(list.get(0),"UTF-8");
					ins =new FileInputStream(new File(folder.getPath()+File.separator+fileName));	
					int size=ins.available();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
					response.addHeader("Content-Length", ""+size);
					response.setContentType("application/octet-stream;charset=UTF-8");
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream()); 					
					
					byte[] buffer = new byte[size];
					ins.read(buffer); 
					outputStream.write(buffer); 
//					int b = 0;  
//		            byte[] buffer = new byte[1024];  
//		            while (b != -1){  
//		                b = ins.read(buffer);  		                
//		                outputStream.write(buffer,0,b);  
//		            }  
		            ins.close();										
					outputStream.flush();  	
					outputStream.close();
				}catch (Exception e) {
					
		        }
				finally {
		            if (ins != null) {
		                try {
		                	ins.close();
		                } catch (Exception e1) {
		                }
		            }
		        }
			}		
		}			
		
									
		return null;
					
	}
	
	/**
	 * 下載最新版本的MEIK程序
	 * download meik report
	 * @param response
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/downloadMEIKReport")	
	public String downloadMEIKReport(HttpServletRequest request,HttpServletResponse response){	
		String realPath = request.getSession().getServletContext().getRealPath("/");
		File folder=new File(realPath,File.separator+"download"+File.separator+"MEIKReport");	
		if(folder.exists()){
			File[] fileList = folder.listFiles();	
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					list.add(fileList[i].getName());
				}			
			}
			Collections.reverse(list);						
			//Tversion version=versionService.loadLatestVersion(1);			
			if(list.size()>0){	
				InputStream ins=null;
				try{				
					String fileName=URLEncoder.encode(list.get(0),"UTF-8");
					ins =new FileInputStream(new File(folder.getPath()+File.separator+fileName));	
					int size=ins.available();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
					response.addHeader("Content-Length", ""+size);
					response.setContentType("application/octet-stream;charset=UTF-8");
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream()); 
					byte[] buffer = new byte[size];
					ins.read(buffer); 
					outputStream.write(buffer); 
//					int b = 0;  
//		            byte[] buffer = new byte[512];  
//		            while (b != -1){  
//		                b = ins.read(buffer);  		                
//		                outputStream.write(buffer,0,b);  
//		            }  
		            ins.close();										
					outputStream.flush();  	
					outputStream.close();
				}catch (Exception e) {
					
		        }
				finally {
		            if (ins != null) {
		                try {
		                	ins.close();
		                } catch (Exception e1) {
		                }
		            }
		        }
			}		
		}					
									
		return null;			
	}
	
	/**
	 * 下載最新版本的MEIK程序
	 * download meik tool
	 * @param response
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/downloadMEIKTool")	
	public String downloadMEIKTool(HttpServletRequest request,HttpServletResponse response){	
		String realPath = request.getSession().getServletContext().getRealPath("/");
		File folder=new File(realPath,File.separator+"download"+File.separator+"MEIKTool");	
		if(folder.exists()){
			File[] fileList = folder.listFiles();	
			List<String> list=new ArrayList<String>();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					list.add(fileList[i].getName());
				}			
			}
			Collections.reverse(list);						
			//Tversion version=versionService.loadLatestVersion(1);			
			if(list.size()>0){	
				InputStream ins=null;
				try{				
					String fileName=URLEncoder.encode(list.get(0),"UTF-8");
					ins =new FileInputStream(new File(folder.getPath()+File.separator+fileName));					
					int size=ins.available();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
					response.addHeader("Content-Length", ""+size);
					response.setContentType("application/octet-stream;charset=UTF-8");
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream()); 
					byte[] buffer = new byte[size];
					ins.read(buffer); 
					outputStream.write(buffer); 
//					int b = 0;  
//		            byte[] buffer = new byte[512];  
//		            while (b != -1){  
//		                b = ins.read(buffer);  		                
//		                outputStream.write(buffer,0,b);  
//		            }  
		            ins.close();										
					outputStream.flush();  	
					outputStream.close();
				}catch (Exception e) {
					
		        }
				finally {
		            if (ins != null) {
		                try {
		                	ins.close();
		                } catch (Exception e1) {
		                }
		            }
		        }
			}		
		}					
									
		return null;			
	}
	
	/**
	 * 接收MEIK Screen上傳的掃描記錄
	 * @param request
	 * @param token
	 * @param jsonStr
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/sendRecords",method=RequestMethod.POST)
	@ResponseBody
	public String sendRecords(@RequestBody String jsonStr){		
		JSONObject respJson = new JSONObject();													
		try{
			long currentTimeMillis=System.currentTimeMillis();
			JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
			String recordsStr=jsonObj.getString("records");
			JSONArray recordsArr=JSON.parseArray(recordsStr);
			for (Object object : recordsArr) {
				JSONObject obj=(JSONObject)object;
				Trecords record=new Trecords();
				record.setCode(obj.getString("code"));	
				record.setClientName(obj.getString("clientname"));
				record.setDeviceId(obj.getString("deviceid"));		
				record.setDescription(obj.getString("description"));
				record.setLicense(obj.getString("license"));
				String screenTimeStr=obj.getString("screentime");
				SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date screenTime = null;
				try {
					screenTime = simpleDateFormat.parse(screenTimeStr);				
					record.setScreenTime(screenTime.getTime());
				} catch (ParseException e) {			
				}
				record.setUploadTime(currentTimeMillis);
				recordsService.createRecord(record);		
			}				
			respJson.put("status", 1);			
		}catch (Exception e) {		
			respJson.put("status", 0);				
			return JSON.toJSONString(respJson);
		}
		return JSON.toJSONString(respJson);
	}
	
	
	/**
	 * 搜索查询用户记录接口(MEIK Screen可以用此接口搜索已登记的用户)
	 * @param request
	 * @param token
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value="/getClientInfo",method=RequestMethod.POST)
	@ResponseBody
	public String getClientInfo(HttpServletRequest request,@RequestHeader("Authorization") String token,@RequestBody String jsonStr){
		TadminUser admin=adminUserService.getTadminUsersByToken(token);
		JSONObject respJson = new JSONObject();	
		if (admin== null) {
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_9");
			return JSON.toJSONString(respJson);
		}
		
		List<TuserInfo> userInfoList=new ArrayList<TuserInfo>();
		JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
		if(jsonObj==null){
			respJson.put("status", 0);				
			respJson.put("info", "The search parameter is required.");
			return JSON.toJSONString(respJson);			
		}
		if (jsonObj.getString("clientId")== null ||jsonObj.getString("clientId").isEmpty()) {
			String firstName=jsonObj.getString("firstName");
			String lastName=jsonObj.getString("lastName");
			String otherName=jsonObj.getString("otherName");
			String birthday=jsonObj.getString("birthday");
			String mobile=jsonObj.getString("mobile");
			String email=jsonObj.getString("email");											
			userInfoList=userInfoService.loadUserInfo(firstName,lastName,otherName,birthday,mobile,email);			
		}		
		else{
			TuserInfo userInfo=userInfoService.getUserInfoById(Integer.parseInt(jsonObj.getString("clientId")));			
			if(userInfo!=null){
				userInfoList.add(userInfo);
			}			
		}
		
		if(userInfoList!=null&&userInfoList.size()>0){				
			respJson.put("data", userInfoList);
			respJson.put("status", 1);
		}
		else{
			respJson.put("status", 0);	
			respJson.put("info", "API_MSG_15");
		}
		return JSON.toJSONString(respJson);
	}
	
}

package com.nuvomed.action;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.nuvomed.dto.TadminJob;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.TuserData;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.AdminJobService;
import com.nuvomed.service.AdminUserService;
import com.nuvomed.service.GroupUserService;
import com.nuvomed.service.UserDataService;


@Controller
@RequestMapping("/report")
public class ReportController extends BaseController{	
	@Autowired
	private AdminJobService adminJobService;
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private UserDataService userDataService;
	@Autowired
	private GroupUserService groupUserService;
		
				
	@RequestMapping(value="/operator",method=RequestMethod.GET)
	public ModelAndView operatorReport(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();	
		TadminUser tUser=getSessionUser(request);
		if(tUser!=null){
			//如果是超级管理员角色，则可以指定给所有医生和报告管理员
			if(tUser.getAdminRole().getRoleId()==1){
				mav.addObject("doctorList", adminUserService.loadAllDoctorList());
			}
			else{
				mav.addObject("doctorList", groupUserService.getGroupDoctorsByUser(tUser.getAdminId()));
			}
			
			mav.setViewName("report/operator");
		}
		else{
			tUser=new TadminUser();		
			mav.addObject("user", tUser);
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping(value="/operator/list",method=RequestMethod.GET)
	@ResponseBody
	public String operatorList(HttpServletRequest request,DataTableParamter dtp){		
		TadminUser tUser=getSessionUser(request);		
		if(tUser!=null){
			PagingData pagingData=null;
			pagingData=adminJobService.loadReportList(dtp,tUser);
			if(pagingData!=null){
				pagingData.setSEcho(dtp.sEcho);
				if(pagingData.getAaData()==null){
					Object[] objs=new Object[]{};
					pagingData.setAaData(objs);
				}
			}
			String doctorListJson= JSON.toJSONString(pagingData);			
			return doctorListJson;	
		}
		return "";
	}
	
	@RequestMapping(value="/operator/excel",method=RequestMethod.POST)	
	public String operatorExcel(HttpServletRequest request,HttpServletResponse response){		
		TadminUser tUser=getSessionUser(request);		
		if(tUser!=null){
			TadminJob adminJob=new TadminJob();			
			adminJob.setCode(request.getParameter("code"));
			adminJob.setCreatedBy(request.getParameter("createdBy"));
			if(!request.getParameter("type").isEmpty()){
				adminJob.setType(Integer.parseInt(request.getParameter("type")));
			}
			adminJob.setDoctor(request.getParameter("adminUser.adminId"));
			adminJob.setStartTime(request.getParameter("startTime"));
			adminJob.setEndTime(request.getParameter("endTime"));
			List<TadminJob> jobList=new ArrayList<TadminJob>();
			
			jobList=adminJobService.loadReportList(adminJob,tUser);									
			//创建HSSFWorkbook对象  
			HSSFWorkbook wkb = new HSSFWorkbook();  
			//创建HSSFSheet对象  
			HSSFSheet sheet = wkb.createSheet("sheet");  
			//创建HSSFRow对象  
			HSSFRow hrow = sheet.createRow(0);  
			hrow.createCell(0).setCellValue("ID");
			hrow.createCell(1).setCellValue("检测编号");
			hrow.createCell(2).setCellValue("客户名称");
			hrow.createCell(3).setCellValue("检测员");
			hrow.createCell(4).setCellValue("上传数据时间");
			hrow.createCell(5).setCellValue("指定医生");
			hrow.createCell(6).setCellValue("指定时间");			
			hrow.createCell(7).setCellValue("上传报告时间");
			hrow.createCell(8).setCellValue("任务关闭时间");					
			  			
			int i=1;
			for (TadminJob job : jobList) {				
				HSSFRow row = sheet.createRow(i);
				row.createCell(0).setCellValue(job.getJobId());
				row.createCell(1).setCellValue(job.getCode());
				row.createCell(2).setCellValue(job.getClientName());
				row.createCell(3).setCellValue(job.getCreatedBy());
				row.createCell(4).setCellValue(job.getCreatedTimeStr());
				row.createCell(5).setCellValue(job.getDoctor());
				row.createCell(6).setCellValue(job.getAssignTimeStr());				
				row.createCell(7).setCellValue(job.getDoneTimeStr());
				row.createCell(8).setCellValue(job.getCloseTimeStr());									
				i++;
			}
			OutputStream outputStream;
			try {
				outputStream = new BufferedOutputStream(response.getOutputStream());
				wkb.write(outputStream);
				response.setHeader("Content-Disposition", "attachment; filename=\"OperatorReport.xls\"");
				//response.addHeader("Content-Length", ""+userData.getStream().length);
				response.setContentType("application/vnd.ms-excel;charset=UTF-8");								
				outputStream.flush();  	
				outputStream.close();
			} catch (IOException e) {
				
			}			
		}
		return null;
	}
	
	/**
	 * 下载扫描后原始上传的数据Zip文件
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value="downloadScreenZip/{id}",method=RequestMethod.GET)	
	public String downloadScreenZip(HttpServletResponse response, @PathVariable String id){
			
		TadminJob job=adminJobService.getAdminJobById(Integer.parseInt(id));
		TuserData userData=userDataService.loadScreenZip(job.getUser().getUserId());		
							
		try{
			if(userData!=null){
				if(job.getDownloadTime()==null){
					job.setDownloadTime(System.currentTimeMillis());
					adminJobService.updateAdminJob(job);
				}
				//String fileName=URLEncoder.encode(userData.getFileName(),"UTF-8");
				
				response.setHeader("Content-Disposition", "attachment; filename=\"" + userData.getFileName() + "\"");
				response.addHeader("Content-Length", ""+userData.getStream().length);
				response.setContentType("application/octet-stream;charset=UTF-8");
				OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
				outputStream.write(userData.getStream());  
				outputStream.flush();  	
				outputStream.close();
			}
			
		}catch (Exception e) {
			
        }			
		return null;
	}
	
	/**
	 * 下载医生最终完成的Zip文件
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value="downloadDocotrZip/{id}",method=RequestMethod.GET)	
	public String downloadDoctorZip(HttpServletResponse response, @PathVariable String id){
			
		TadminJob job=adminJobService.getAdminJobById(Integer.parseInt(id));
		TuserData userData=userDataService.loadDoctorZip(job.getUser().getUserId());		
							
		try{
			if(userData!=null){
				if(job.getDownloadTime()==null){
					job.setDownloadTime(System.currentTimeMillis());
					adminJobService.updateAdminJob(job);
				}
				//String fileName=URLEncoder.encode(userData.getFileName(),"UTF-8");
				
				response.setHeader("Content-Disposition", "attachment; filename=\"" + userData.getFileName() + "\"");
				response.addHeader("Content-Length", ""+userData.getStream().length);
				response.setContentType("application/octet-stream;charset=UTF-8");
				OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
				outputStream.write(userData.getStream());  
				outputStream.flush();  	
				outputStream.close();
			}
			
		}catch (Exception e) {
			
        }			
		return null;
	}
}

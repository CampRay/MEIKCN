package com.nuvomed.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the admin_job database table.
 * 
 */
public class TadminJob implements Serializable {
	private static final long serialVersionUID = 1L;

	private int jobId;
	
	private int userId;	
	/**
	 * 任务阶段类型：
	 *1 操作員成功上传扫描数据 (Data Uploaded)；
	 *2 管理员指定医生编辑报表(Data Assigned)；
	 *3 醫生已成功下載數據(Data Downloaded)
	 *4医生完成报表並上傳報表成功(Report Uploaded) 
	 *5 系統自動指定接收報表的系統醫生(Report Assigned)
	 *6 系統醫生成功下載報表(Report Downloaded)
	 *7 系統醫生上傳報告成功，操作員可下載(Report Ready)
	 */
	private int type;			
	
	private boolean status;
			
	private Long createdTime;
	
	private Long assignTime;
	
	private Long reportTime;
	
	private Long doneTime;		
	
	private Long closeTime;
	
	private Long downloadTime;
	//病人
	private Tuser user;
	//操作员
	private String createdBy;
	
	//工作指派的用户
	private TadminUser adminUser;		
	private String doctor;
	
	private String code;
	
	private String clientName;
	

		
	private String createdTimeStr;
	
	private String assignTimeStr;
	
	private String reportTimeStr;
	
	private String doneTimeStr;
	
	private String closeTimeStr;
	
	private String downloadTimeStr;
	
	private String startTime;
	
	private String endTime;
		
	
	private String mobile;
	
	private String email;
	
	private String birthday;
	
	private String cid;
	
	private Integer screenResult;
	
	private boolean missingData=false;
	
	public TadminJob() {
	}


	public int getJobId() {
		return jobId;
	}


	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	


	public String getCid() {
		if(user!=null&&user.getAdminUser()!=null){
			cid=user.getAdminUser().getAdminId();
		}
		return cid;
	}


	public void setCid(String cid) {
		this.cid = cid;
	}


	public int getUserId() {
		if(user!=null){
			userId=user.getUserId();
		}
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getMobile() {
		if(user!=null){
			mobile=user.getMobile();
		}
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getEmail() {
		if(user!=null){
			email=user.getEmail();
		}
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getBirthday() {
		if(user!=null){
			birthday=user.getBirthday();
		}
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Long getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}


	public Long getDoneTime() {
		return doneTime;
	}


	public void setDoneTime(Long doneTime) {
		this.doneTime = doneTime;
	}


	public TadminUser getAdminUser() {
		return adminUser;
	}


	public void setAdminUser(TadminUser adminUser) {
		this.adminUser = adminUser;
	}


	public Tuser getUser() {
		return user;
	}


	public void setUser(Tuser user) {
		this.user = user;
	}
	
	public String getCreatedTimeStr() {
		if(createdTime!=null){
			Date date=new Date(createdTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
		}else
		return this.createdTimeStr;
	
	}
	
	public String getDoneTimeStr() {
		if(doneTime!=null){
			Date date=new Date(doneTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
		}else
		return this.doneTimeStr;
	
	}


	public Long getAssignTime() {
		return assignTime;
	}


	public void setAssignTime(Long assignTime) {
		this.assignTime = assignTime;
	}


	public Long getReportTime() {
		return reportTime;
	}


	public void setReportTime(Long reportTime) {
		this.reportTime = reportTime;
	}


	public Long getCloseTime() {
		return closeTime;
	}


	public void setCloseTime(Long closeTime) {
		this.closeTime = closeTime;
	}


	public String getAssignTimeStr() {		
		if(assignTime!=null){
			Date date=new Date(assignTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
		}else
		return this.assignTimeStr;
	}


	public void setAssignTimeStr(String assignTimeStr) {
		this.assignTimeStr = assignTimeStr;
	}


	public String getReportTimeStr() {		
		if(reportTime!=null){
			Date date=new Date(reportTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
		}else
		return this.reportTimeStr;
	}


	public void setReportTimeStr(String reportTimeStr) {
		this.reportTimeStr = reportTimeStr;
	}


	public String getCloseTimeStr() {		
		if(closeTime!=null){
			Date date=new Date(closeTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
		}else
		return this.closeTimeStr;
	}


	public void setCloseTimeStr(String closeTimeStr) {
		this.closeTimeStr = closeTimeStr;
	}


	public void setCreatedTimeStr(String createdTimeStr) {
		this.createdTimeStr = createdTimeStr;
	}


	public void setDoneTimeStr(String doneTimeStr) {
		this.doneTimeStr = doneTimeStr;
	}


	public String getDoctor() {
		if(adminUser!=null){
			return adminUser.getAdminId();
		}
		else{
			return this.doctor;
		}
	}


	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}


	public String getCode() {
		if(user!=null){
			return user.getCode();
		}
		else{
			return this.code;
		}		
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getClientName() {
		if(user!=null){
			String userName=user.getLastName();
			if(user.getFirstName()!=null&&!user.getFirstName().isEmpty()){
				userName=userName+", "+user.getFirstName();
			}
			if(user.getOtherName()!=null&&!user.getOtherName().isEmpty()){
				userName=userName+" "+user.getOtherName();
			}			
			return userName;
		}
		else{
			return this.clientName;
		}	
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public Long getDownloadTime() {
		return downloadTime;
	}


	public void setDownloadTime(Long downloadTime) {
		this.downloadTime = downloadTime;
	}


	public String getDownloadTimeStr() {
		if(downloadTime!=null){
			Date date=new Date(downloadTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
		}else
		return this.downloadTimeStr;		
	}


	public void setDownloadTimeStr(String downloadTimeStr) {
		this.downloadTimeStr = downloadTimeStr;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	

	public Integer getScreenResult() {
		if(user!=null){
			screenResult=user.getResult();
		}				
		return screenResult;
	}


	public void setScreenResult(Integer screenResult) {
		this.screenResult = screenResult;
	}


	public boolean isMissingData() {
		if(user!=null){
			missingData=user.getMissingData()==null?false:user.getMissingData();
		}			
		return missingData;
	}


	public void setMissingData(boolean missingData) {
		this.missingData = missingData;
	}
		
	
}
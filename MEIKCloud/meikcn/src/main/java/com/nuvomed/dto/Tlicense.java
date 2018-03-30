package com.nuvomed.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the admin_user database table.
 * 
 */
public class Tlicense implements Serializable {
	
	private static final long serialVersionUID = -8658472374205094291L;

	private String license;	

	private String cpuId;		
	
	private Long activeTime;
	
	private String activeTimeStr;
		
	private String deviceId;
	
	private Long deadline;
	
	private String deadlineStr;
		
	private boolean status;
	
	private Long createdTime;
	
	private String createdTimeStr;
	
	private int type;

	public Tlicense() {
	}


	public String getCpuId() {
		return cpuId;
	}


	public void setCpuId(String cpuId) {
		this.cpuId = cpuId;
	}


	public Long getActiveTime() {
		return activeTime;
	}


	public void setActiveTime(Long activeTime) {
		this.activeTime = activeTime;
	}


	public String getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}





	public String getLicense() {
		return license;
	}





	public void setLicense(String license) {
		this.license = license;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getActiveTimeStr() {
		if(activeTime!=null){
			Date date=new Date(activeTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
		}else
		return this.activeTimeStr;		
	}


	public void setActiveTimeStr(String activeTimeStr) {
		this.activeTimeStr = activeTimeStr;
	}


	public Long getDeadline() {
		return deadline;
	}


	public void setDeadline(Long deadline) {
		this.deadline = deadline;
	}


	public String getDeadlineStr() {
		if((deadlineStr==null||deadlineStr.isEmpty())&&deadline!=null){
			Date date=new Date(deadline);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			return sdf.format(date);		
		}else
		return deadlineStr;
	}


	public void setDeadlineStr(String deadlineStr) {
		this.deadlineStr = deadlineStr;
	}


	public Long getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}


	public String getCreatedTimeStr() {
		if(createdTime!=null){
			Date date=new Date(createdTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
		}else
		return createdTimeStr;
	}


	public void setCreatedTimeStr(String createdTimeStr) {
		this.createdTimeStr = createdTimeStr;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}
		

}
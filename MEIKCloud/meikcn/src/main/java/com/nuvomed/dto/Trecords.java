package com.nuvomed.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the admin_user database table.
 * 
 */
public class Trecords implements Serializable {
	
	private static final long serialVersionUID = -8658472374205094291L;

	private int id;	
		
	private String code;
	
	private String clientName;
	
	private String deviceId;	
	
	private Long screenTime;
	
	private String screenTimeStr;
	
	private String description;
	
	private String license;
	
	private Long uploadTime;
	
	private String uploadTimeStr;
	
	public Trecords() {
	}	

	public String getLicense() {
		return license;
	}


	public void setLicense(String license) {
		this.license = license;
	}

	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getCode() {
		return code;
	}





	public void setCode(String code) {
		this.code = code;
	}





	public String getClientName() {
		return clientName;
	}





	public void setClientName(String clientName) {
		this.clientName = clientName;
	}





	public String getDeviceId() {
		return deviceId;
	}





	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}





	public Long getScreenTime() {
		return screenTime;
	}





	public void setScreenTime(Long screenTime) {
		this.screenTime = screenTime;
	}





	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	public String getScreenTimeStr() {
		if(screenTime!=null){
			Date date=new Date(screenTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
		}else
		return screenTimeStr;
	}


	public void setScreenTimeStr(String screenTimeStr) {
		this.screenTimeStr = screenTimeStr;
	}

	public Long getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Long uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadTimeStr() {
		if(uploadTime!=null){
			Date date=new Date(uploadTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
		}else		
		return uploadTimeStr;
	}

	public void setUploadTimeStr(String uploadTimeStr) {
		this.uploadTimeStr = uploadTimeStr;
	}
		

}
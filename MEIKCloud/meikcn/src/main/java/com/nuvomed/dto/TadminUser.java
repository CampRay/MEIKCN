package com.nuvomed.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the admin_user database table.
 * 
 */
public class TadminUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String adminId;	

	private String email;	

	private String password;	

	private boolean status;
	
	private String createdBy;
	
	private Long createdTime;

	private String updatedBy;
	
	private Long updatedTime;
	
	private String createdTimeStr;	
	
	private String updatedTimeStr;
	
	private String token;
	
	@SuppressWarnings("unused")
	private String roleName;

	private TadminRole adminRole;
	
	private TadminInfo adminInfo; 

	public TadminUser() {
	}
	
	public TadminUser(String adminId,String email,String password,TadminRole adminRole,boolean status) {
		this.adminId=adminId;
		this.email=email;
		this.password=password;
		this.adminRole=adminRole;
		this.status=status;
	}
	

	
	
	
	public String getRoleName() {
		return adminRole.getRoleName();
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreatedTimeStr() {
		if(createdTime!=null){
		Date date=new Date(createdTime);
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return sdf.format(date);		
		}else
		return this.createdTimeStr;
	
	}

	public void setCreatedTimeStr(String createdTimeStr) {
		this.createdTimeStr = createdTimeStr;
	}

	public String getUpdatedTimeStr() {
		if(updatedTime!=null){
		Date date=new Date(updatedTime);
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return sdf.format(date);	
		}
		else
			return this.updatedTimeStr;
	
	}

	public void setUpdatedTimeStr(String updatedTimeStr) {
		this.updatedTimeStr = updatedTimeStr;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public TadminRole getAdminRole() {
		return this.adminRole;
	}

	public void setAdminRole(TadminRole adminRole) {
		this.adminRole = adminRole;
	}

	/**
	 * @return the adminId
	 */
	public String getAdminId() {
		return adminId;
	}

	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return the createdTime
	 */
	public Long getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the adminInfo
	 */
	public TadminInfo getAdminInfo() {
		return adminInfo;
	}

	/**
	 * @param adminInfo the adminInfo to set
	 */
	public void setAdminInfo(TadminInfo adminInfo) {
		this.adminInfo = adminInfo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
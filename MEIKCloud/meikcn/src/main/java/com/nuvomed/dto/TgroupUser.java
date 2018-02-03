package com.nuvomed.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the group_user database table.
 * 
 */
public class TgroupUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;	
	
	private int groupId;	
	
	private String adminId;

	private Tgroup group;		
	
	private TadminUser adminUser;
	
	private Long createdTime;
	
	private String createdTimeStr;
	
	private String email;
	
	private String roleName;

	public TgroupUser() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
		
	public int getGroupId() {
		if(group!=null){
			return group.getId();
		}
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getAdminId() {
		if(adminUser!=null){
			return adminUser.getAdminId();
		}
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}	

	public String getEmail() {
		if(adminUser!=null){
			return adminUser.getEmail();
		}
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleName() {
		if(adminUser!=null){
			return adminUser.getRoleName();
		}
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
		return this.createdTimeStr;		
	}

	public void setCreatedTimeStr(String createdTimeStr) {
		this.createdTimeStr = createdTimeStr;
	}

	public Tgroup getGroup() {
		return group;
	}

	public void setGroup(Tgroup group) {
		this.group = group;
	}

	public TadminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(TadminUser adminUser) {
		this.adminUser = adminUser;
	}
		

}
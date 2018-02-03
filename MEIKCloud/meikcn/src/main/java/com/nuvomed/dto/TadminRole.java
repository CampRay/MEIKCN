package com.nuvomed.dto;

import java.io.Serializable;


/**
 * The persistent class for the admin_role database table.
 * 
 */
public class TadminRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private int roleId;

	private int pid;

	private String roleName;

	private boolean status;	
	
	private TadminRoleRights adminRoleRights;

	public TadminRole() {
	}
	
	public TadminRole(int roleId,String roleName,int pid,boolean status) {
		this.roleId=roleId;
		this.roleName=roleName;
		this.pid=pid;
		this.status=status;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public TadminRoleRights getAdminRoleRights() {
		return adminRoleRights;
	}

	public void setAdminRoleRights(TadminRoleRights adminRoleRights) {
		this.adminRoleRights = adminRoleRights;
	}	

}
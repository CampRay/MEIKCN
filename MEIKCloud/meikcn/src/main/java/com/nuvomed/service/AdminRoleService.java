package com.nuvomed.service;

import java.util.List;

import com.nuvomed.dto.TadminRole;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;

public interface AdminRoleService {
	
	TadminRole getAdminRoleById(int roleId);
	
	List<TadminRole> getAllAdminRoles();
	List<TadminRole> getAllWorkRoles();
	
	void createAdminRole(TadminRole adminRole);
	
	void updateAdminRole(TadminRole adminRole);
	
	void deleteAdminRole(TadminRole adminRole);
	
	void deleteAdminRoleById(int id);
	
	void deleteAdminRolesByIds(Integer[] ids);
	
	PagingData loadAdminRolesList(DataTableParamter rdtp);
		
}

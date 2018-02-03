package com.nuvomed.service;

import java.util.List;

import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.TgroupUser;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;

public interface GroupUserService {
	
	TgroupUser getGroupUserById(int id);
	
	TgroupUser getGroupUser(int groupId, String adminId);
	public List<TgroupUser> getGroupUser(String adminId);
	
	List<TgroupUser> getAllGroupUsers();
	
	void createGroupUser(TgroupUser groupUser);
	
	void updateGroupUser(TgroupUser groupUser);
	
	void deleteGroupUser(TgroupUser groupUser);
	
	void deleteGroupUserById(int id);
	
	void deleteGroupUsersByIds(Integer[] ids);
	
	PagingData loadGroupUsersList(DataTableParamter rdtp);
	
	PagingData loadGroupUserListById(String id, DataTableParamter rdtp);
	
	List<String> getAllSameGroupUsers(String adminId);
	
	List<TadminUser> getAllSameGroupUsers(TadminUser adminUser);
	

	public List<TadminUser> getGroupManagerIdsByUser(TadminUser adminUser);
	public List<TadminUser> getGroupManagerIdsByUser(String adminUserID);	
	public List<TadminUser> getGroupDoctorsByUser(String adminUserID);
		
}

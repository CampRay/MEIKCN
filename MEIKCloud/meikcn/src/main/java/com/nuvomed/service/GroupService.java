package com.nuvomed.service;

import java.util.List;

import com.nuvomed.dto.Tgroup;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;

public interface GroupService {
	
	Tgroup getGroupById(int id);
	
	List<Tgroup> getAllGroups();
	
	void createGroup(Tgroup group);
	
	void updateGroup(Tgroup group);
	
	void deleteGroup(Tgroup group);
	
	void deleteGroupById(int id);
	
	void deleteGroupsByIds(Integer[] ids);
	
	public Tgroup loadGroupByName(String groupName);
	
	PagingData loadGroupsList(DataTableParamter rdtp);
	
	public void activateByIds(Integer[] ids);
	
	public void deactivateByIds(Integer[] ids);
		
		
}

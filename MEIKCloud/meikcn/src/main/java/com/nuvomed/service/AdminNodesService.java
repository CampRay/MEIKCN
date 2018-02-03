package com.nuvomed.service;

import java.util.List;

import com.nuvomed.dto.TadminNodes;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;

public interface AdminNodesService {
	
	public TadminNodes getAdminNodeById(int nodeId);
	
	public List<TadminNodes> getAllAdminNodes();
	
	public List<TadminNodes> getAllEnabledAdminNodes();
	
	public List<TadminNodes> getAllAdminNodesMenu();
	
	public List<TadminNodes> getAdminNodesMenuByPid(Integer pid);
	
	public void createAdminNode(TadminNodes adminNode);
	
	public void updateAdminNode(TadminNodes adminNode);
	
	public void deleteAdminNode(TadminNodes adminNode);
	
	public void deleteAdminNodeById(int id);
	
	public void deleteAdminNodesByIds(Integer[] ids);
	
	public PagingData loadAdminNodesList(DataTableParamter rdtp);
	
	void cachedNodesData();
		
}

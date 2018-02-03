package com.nuvomed.service;

import com.nuvomed.dto.Tversion;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;

public interface VersionService {
	
	Tversion getVersionById(int id);
	
	void createVersion(Tversion license);
	
	void updateVersion(Tversion license);	
	
	void deleteVersion(Tversion license);
	
	void deleteVersionById(String id);
	
	void deleteVersionByIds(String[] ids);
	
	
	public PagingData loadVersionList(DataTableParamter rdtp);	
	
	public Tversion loadLatestVersion(int type);
		
}

package com.nuvomed.service;

import com.nuvomed.dto.TadminLog;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;

public interface AdminuserLogService {

	

	TadminLog getRuleLogById(Integer Id);
	
	void createAdminLog(TadminLog adminlog);
	
	void deleteRuleLog(TadminLog adminlog);
	
	void deleteRuleLogById(int id);
	
	void deleteRuleLogById(Integer[] ids);
	
	public PagingData loadAdminLogList(DataTableParamter rdtp);
	
	public PagingData loadAdminLogList(String id,DataTableParamter rdtp);
	
}

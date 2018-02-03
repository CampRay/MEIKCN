package com.nuvomed.service;

import com.nuvomed.dto.Trecords;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;

public interface RecordsService {
	
	Trecords getRecordById(int id);
	
	void createRecord(Trecords record);
	
	void updateRecord(Trecords record);	
	
	void deleteRecord(Trecords record);
	
	void deleteRecordById(Integer id);
	
	void deleteRecordsByIds(Integer[] ids);
	
	
	public PagingData loadRecordsList(DataTableParamter rdtp);			
		
}

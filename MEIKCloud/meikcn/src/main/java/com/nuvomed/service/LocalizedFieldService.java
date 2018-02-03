package com.nuvomed.service;

import java.util.List;

import com.nuvomed.dto.TlocalizedField;

public interface LocalizedFieldService {
	
	void createLocalizedFieldList(TlocalizedField[] locals);
	
	void createLocalizedFieldList(List<TlocalizedField> locals);
	
	void updateLocalizedFieldList(List<TlocalizedField> locals);
	
	void createLocalizedField(TlocalizedField local);
	
	void updateLocalizedField(TlocalizedField local);
	
	List<TlocalizedField> getListByEntityIdAndEntityName(Integer entityId,String tableName);
	
	List<TlocalizedField> getLocalizedField(Integer entityId,String tableName,String fieldName);
	
	TlocalizedField getLocalizedField(Integer entityId,String local,String tableName,String fieldName);
	
	TlocalizedField getLocalizedValue(Integer entityId, Integer languageId,String tableName, String fieldName);
	
}

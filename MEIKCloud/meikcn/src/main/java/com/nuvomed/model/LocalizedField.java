package com.nuvomed.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.nuvomed.dto.TlocalizedField;

public class LocalizedField implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4111509436294424261L;
	private Integer localeId;
	private Integer languageId;
	private Integer entityId;
	private String tableName;
	private String tableField;
	private String localeValue;
	public Integer getLocaleId() {
		return localeId;
	}
	public void setLocaleId(Integer localeId) {
		this.localeId = localeId;
	}
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableField() {
		return tableField;
	}
	public void setTableField(String tableField) {
		this.tableField = tableField;
	}
	public String getLocaleValue() {
		return localeValue;
	}
	public void setLocaleValue(String localeValue) {
		this.localeValue = localeValue;
	}
	
	public LocalizedField(){}
	
	public LocalizedField(Integer localeId, Integer languageId,
			Integer entityId, String tableName, String tableField,
			String localeValue) {
		this.localeId = localeId;
		this.languageId = languageId;
		this.entityId = entityId;
		this.tableName = tableName;
		this.tableField = tableField;
		this.localeValue = localeValue;
	}
	public static List<LocalizedField> setValues(List<TlocalizedField> locals){
		 List<LocalizedField> loc = new ArrayList<LocalizedField>();
		for (TlocalizedField tlocalizedField : locals) {
			loc.add(new LocalizedField(tlocalizedField.getLocaleId(),tlocalizedField.getLanguage().getId(),tlocalizedField.getEntityId(),tlocalizedField.getTableName(),tlocalizedField.getTableField(),tlocalizedField.getLocaleValue()));
		}
		return loc;
	}
	
}

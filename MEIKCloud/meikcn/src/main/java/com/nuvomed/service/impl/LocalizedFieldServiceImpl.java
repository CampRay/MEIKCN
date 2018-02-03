package com.nuvomed.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvomed.dao.LanguageDao;
import com.nuvomed.dao.LocalizedFieldDao;
import com.nuvomed.dto.Tlanguage;
import com.nuvomed.dto.TlocalizedField;
import com.nuvomed.service.LocalizedFieldService;
@Service
public class LocalizedFieldServiceImpl implements LocalizedFieldService{
	
	@Autowired
	LocalizedFieldDao localizedFieldDao;
	@Autowired
	LanguageDao languageDao;
	
	public void createLocalizedFieldList(List<TlocalizedField> locals) {
		// TODO Auto-generated method stub
		if(locals!=null&&locals.size()>0){
			for (TlocalizedField tlocalizedField : locals) {
				if(tlocalizedField!=null&&tlocalizedField.getEntityId()!=null&&!tlocalizedField.getLocaleValue().isEmpty()){
					localizedFieldDao.save(tlocalizedField);
				}
			}
		}
		
	}

	
	public void updateLocalizedFieldList(List<TlocalizedField> locals) {
		// TODO Auto-generated method stub
		if(locals!=null&&locals.size()>0){
			for (TlocalizedField tlocalizedField : locals) {
				localizedFieldDao.update(tlocalizedField);
			}
		}
	}

	
	public void createLocalizedField(TlocalizedField local) {
		// TODO Auto-generated method stub
		localizedFieldDao.save(local);
	}

	
	public void updateLocalizedField(TlocalizedField local) {
		// TODO Auto-generated method stub
		localizedFieldDao.update(local);
	}

	
	
	public void createLocalizedFieldList(TlocalizedField[] locals) {
		// TODO Auto-generated method stub
		if(locals!=null&&locals.length>0){
			for (TlocalizedField tlocalizedField : locals) {
				if(tlocalizedField!=null&&tlocalizedField.getEntityId()!=null&&!tlocalizedField.getLocaleValue().isEmpty()){
					localizedFieldDao.save(tlocalizedField);
				}
			}
		}
	}

	
	public List<TlocalizedField> getListByEntityIdAndEntityName(
			Integer entityId, String tableName) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entityId", entityId);
		params.put("tableName", tableName);
		String hql = "from TlocalizedField loc where loc.entityId = :entityId and loc.tableName = :tableName";
		return localizedFieldDao.find(hql, params);
	}
	
	@SuppressWarnings("unchecked")
	public List<TlocalizedField> getLocalizedField(Integer entityId, String tableName, String fieldName) {
		Criteria criteria=localizedFieldDao.createCriteria();
		return criteria.add(Restrictions.eq("entityId", entityId))
				.add(Restrictions.eq("tableName", tableName))
				.add(Restrictions.eq("tableField", fieldName))
				.list();		
	}
	
	public TlocalizedField getLocalizedField(Integer entityId, String local,String tableName, String fieldName) {
		Criteria criteria=localizedFieldDao.createCriteria();
		Tlanguage language = languageDao.findUnique("local", local);
		return (TlocalizedField) criteria.add(Restrictions.eq("entityId", entityId))
				.add(Restrictions.eq("tableName", tableName))
				.add(Restrictions.eq("tableField", fieldName))
				.add(Restrictions.eq("language.id",language.getId())).uniqueResult();		
	}


	
	public TlocalizedField getLocalizedValue(Integer entityId,
			Integer languageId, String tableName, String fieldName) {
		// TODO Auto-generated method stub
		return localizedFieldDao.getLocalizedValue(entityId, languageId, tableName, fieldName);
	}

}

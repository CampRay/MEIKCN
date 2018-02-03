package com.nuvomed.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvomed.dao.LanguageDao;
import com.nuvomed.dto.Tlanguage;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.LanguageService;
@Service
public class LanguageServiceImpl implements LanguageService{
	
	@Autowired
	private LanguageDao languageDao;
	
	public Tlanguage getLanguageById(Integer Id) {
		
		return languageDao.get(Id);
	}

	public void createLanguage(Tlanguage language) {
	languageDao.create(language);
		
	}

	public void updateLanguage(Tlanguage language) {
	languageDao.update(language);
		
	}

	public void deleteLanguage(Tlanguage language) {
	languageDao.delete(language);
		
	}

	public void deleteLanguageByIds(Integer[] ids) {
		if(ids!=null&&ids.length>0){
			for (Integer id : ids) {
				Tlanguage language=getLanguageById(id);
					language.setStatus(false);
					languageDao.update(language);
			}
		}	
	}

	public PagingData loadLanguageList(DataTableParamter rdtp) {
		return languageDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	
		
	}

	public void activeLanguageByids(Integer[] ids) {
		if(ids!=null&&ids.length>0){
			for (Integer id : ids) {
				Tlanguage language=getLanguageById(id);
					language.setStatus(true);
					languageDao.update(language);
			}
		}	
		
	}

	
	@SuppressWarnings("unchecked")
	public List<Tlanguage> loadAllTlanguage() {
		Criteria criteria=languageDao.createCriteria();
		return criteria.add(Restrictions.eq("status",true))				
				.list();
	}

	
	public Tlanguage get(String locale) {
		// TODO Auto-generated method stub
		return languageDao.findUnique("local", locale);
	}
		public Tlanguage getLanguageBylocal(String local) {
		// TODO Auto-generated method stub
		 
		return 	languageDao.getlanguagebylocal(local);	
	
	}


}

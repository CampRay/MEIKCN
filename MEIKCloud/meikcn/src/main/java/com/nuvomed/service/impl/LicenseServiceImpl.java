/**   
 * @Title: AdminUserServiceImpl.java 
 * @Package com.uswop.service 
 *
 * @Description: User Points Management System
 * 
 * @date Sep 11, 2014 7:21:25 PM
 * @version V1.0   
 */ 
package com.nuvomed.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.dao.LicenseDao;

import com.nuvomed.dto.Tlicense;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.LicenseService;

/** 
 * <p>Types Description</p>
 * @ClassName: AdminUserServiceImpl 
 * @author Phills Li 
 * 
 */
@Service
public class LicenseServiceImpl implements LicenseService {
	
	@Autowired
	private LicenseDao licenseDao;

	public Tlicense getLicenseById(String license) {		
		return licenseDao.get(license);
	}

	public void createLicense(Tlicense license) {
		licenseDao.create(license);
	}

	public void updateLicense(Tlicense license) {
		licenseDao.update(license);
		
	}	

	public void deleteLicense(Tlicense license) {
		licenseDao.delete(license);
		
	}

	public void deleteLicenseById(String id) {
		licenseDao.delete(id);
	}

	public void deleteLicenseByIds(String[] ids) {
		licenseDao.deleteAll(ids);
	}

	public PagingData loadLicenseList(DataTableParamter rdtp) {
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					if(key=="status"){
						criterionsList.add(Restrictions.eq(key, jsonObj.getBoolean(key)));
					}
					else{
						criterionsList.add(Restrictions.like(key, jsonObj.get(key)));
					}
				}
			}
			Criterion[] criterions=new Criterion[criterionsList.size()];
			int i=0;
			for (Criterion criterion : criterionsList) {
				criterions[i]=criterion;	
				i++;
			}
			return licenseDao.findPage("createdTime",false,criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return licenseDao.findPage("createdTime",false,rdtp.iDisplayStart, rdtp.iDisplayLength);
	}	

	public void activeLicenseByids(String[] ids) {
		if(ids!=null&&ids.length>0){
			for (String id : ids) {
				Tlicense license=getLicenseById(id);
				license.setStatus(true);
				licenseDao.update(license);
			}
		}	
		
	}
	
	public void deactiveLicenseByids(String[] ids) {
		if(ids!=null&&ids.length>0){
			for (String id : ids) {
				Tlicense license=getLicenseById(id);
				license.setStatus(false);
				licenseDao.update(license);
			}
		}	
		
	}
	
}

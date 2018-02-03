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

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.dao.VersionDao;
import com.nuvomed.dto.Tversion;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.VersionService;

/** 
 * <p>Types Description</p>
 * @ClassName: VersionImpl 
 * @author Phills Li 
 * 
 */
@Service
public class VersionServiceImpl implements VersionService {
	
	@Autowired
	private VersionDao versionDao;

	public Tversion getVersionById(int id) {		
		return versionDao.get(id);
	}

	public void createVersion(Tversion version) {
		versionDao.create(version);
	}

	public void updateVersion(Tversion version) {
		versionDao.update(version);
		
	}	

	public void deleteVersion(Tversion version) {
		versionDao.delete(version);
		
	}

	public void deleteVersionById(String id) {
		versionDao.delete(id);
	}

	public void deleteVersionByIds(String[] ids) {
		versionDao.deleteAll(ids);
	}

	public PagingData loadVersionList(DataTableParamter rdtp) {
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					if(key=="type"){
						criterionsList.add(Restrictions.eq(key, jsonObj.getInteger(key)));
					}
					else{
						criterionsList.add(Restrictions.like(key, val));
					}
				}
			}
			Criterion[] criterions=new Criterion[criterionsList.size()];
			int i=0;
			for (Criterion criterion : criterionsList) {
				criterions[i]=criterion;	
				i++;
			}
			return versionDao.findPage("version",false,criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return versionDao.findPage("version",false,rdtp.iDisplayStart, rdtp.iDisplayLength);
	}	
		
	public Tversion loadLatestVersion(int type) {
		Criteria criteria=versionDao.createCriteria();
		return (Tversion) criteria.add(Restrictions.eq("type", type))
				.addOrder(Order.desc("version")).uniqueResult();				
				
	}
	
}

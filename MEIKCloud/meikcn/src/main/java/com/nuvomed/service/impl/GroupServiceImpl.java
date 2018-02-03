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
import com.nuvomed.dao.GroupDao;
import com.nuvomed.dto.Tgroup;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.GroupService;

/** 
 * <p>Types Description</p>
 * @ClassName: GroupServiceImpl 
 * @author Phills Li 
 * 
 */
@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDao groupDao;

	
	public Tgroup getGroupById(int id) {
		return groupDao.get(id);
	}

	
	public List<Tgroup> getAllGroups() {
		return groupDao.findBy("deleted", false);
	}

	
	public void createGroup(Tgroup group) {
		groupDao.create(group);		
	}

	
	public void updateGroup(Tgroup group) {
		groupDao.update(group);
	}

	
	public void deleteGroup(Tgroup group) {
		groupDao.delete(group);		
	}

	
	public void deleteGroupById(int id) {
		groupDao.delete(id);
	}

	
	public void deleteGroupsByIds(Integer[] ids) {
		groupDao.deleteAll(ids);
	}
	
	
	public Tgroup loadGroupByName(String groupName) {
		return groupDao.findUnique("groupName", groupName);
	}

	
	public PagingData loadGroupsList(DataTableParamter rdtp) {
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					if(key=="deleted"){
						criterionsList.add(Restrictions.eq(key, jsonObj.getBoolean(key)));
					}					
					else{
						criterionsList.add(Restrictions.eq(key, jsonObj.get(key)));
					}
				}
			}
			Criterion[] criterions=new Criterion[criterionsList.size()];
			int i=0;
			for (Criterion criterion : criterionsList) {
				criterions[i]=criterion;	
				i++;
			}
			return groupDao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return groupDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}
		
	
	public void activateByIds(Integer[] ids) {
		groupDao.activateGroups(ids);
		
	}
	
	public void deactivateByIds(Integer[] ids) {
		groupDao.deactivateGroups(ids);		
	}

}

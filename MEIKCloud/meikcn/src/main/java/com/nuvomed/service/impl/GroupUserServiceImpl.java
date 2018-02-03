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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.dao.GroupUserDao;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.TgroupUser;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.GroupUserService;

/** 
 * <p>Types Description</p>
 * @ClassName: AdminUserServiceImpl 
 * @author Phills Li 
 * 
 */
@Service
public class GroupUserServiceImpl implements GroupUserService {
	@Autowired
	private GroupUserDao groupUserDao;

	
	public TgroupUser getGroupUserById(int id) {
		return groupUserDao.get(id);
	}
	
	
	public TgroupUser getGroupUser(int groupId, String adminId) {
		Criteria criteria=groupUserDao.createCriteria();
		criteria=criteria.add(Restrictions.eq("group.id", groupId));
		criteria=criteria.add(Restrictions.eq("adminUser.adminId", adminId));
		return (TgroupUser)criteria.uniqueResult();
	}
	
	/**
	 * 根据用户ID查询组成员记录
	 */
	@SuppressWarnings("unchecked")
	public List<TgroupUser> getGroupUser(String adminId) {
		Criteria criteria=groupUserDao.createCriteria();		
		criteria=criteria.add(Restrictions.eq("adminUser.adminId", adminId));
		return criteria.list();
	}

	
	public List<TgroupUser> getAllGroupUsers() {
		return groupUserDao.LoadAll();
	}

	
	public void createGroupUser(TgroupUser groupUser) {
		groupUserDao.create(groupUser);		
	}

	
	public void updateGroupUser(TgroupUser groupUser) {
		groupUserDao.update(groupUser);
	}

	
	public void deleteGroupUser(TgroupUser groupUser) {
		groupUserDao.delete(groupUser);		
	}

	
	public void deleteGroupUserById(int id) {
		groupUserDao.delete(id);
	}

	
	public void deleteGroupUsersByIds(Integer[] ids) {
		groupUserDao.deleteAll(ids);
	}
		
	
	public PagingData loadGroupUsersList(DataTableParamter rdtp) {
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("MM/dd/yyyy");
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					if(key=="startTime"){
						Date sdate = null;
						try {
							sdate = simpleDateFormat.parse(val);							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Long startLong=sdate.getTime();						
						criterionsList.add(Restrictions.ge("createdTime", startLong));
					}
					else if(key=="endTime"){
						Date edate = null;
						try {
							edate = simpleDateFormat.parse(val);							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Long endLong=edate.getTime();						
						criterionsList.add(Restrictions.le("createdTime", endLong));
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
			return groupUserDao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return groupUserDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}
		
	public PagingData loadGroupUserListById(String id, DataTableParamter rdtp) { 
		Criteria criteria=groupUserDao.createCriteria();
		criteria=criteria.add(Restrictions.eq("group.id", Integer.parseInt(id)));
		return groupUserDao.findPage(criteria,rdtp.iDisplayStart, rdtp.iDisplayLength);
		
	}
	
	/**
	 * 查询与指定用户在同一组的所有用户
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllSameGroupUsers(String adminId){
		List<String> adminIdList=new ArrayList<String>();	
		List<TgroupUser> groupUserList=groupUserDao.findBy("adminUser.adminId", adminId);
		if(groupUserList!=null&&groupUserList.size()>0){
			List<Integer> ids=new ArrayList<Integer>();		
			for (TgroupUser groupUser : groupUserList) {
				ids.add(groupUser.getGroupId());
			}
			
			Criteria criteria=groupUserDao.createCriteria();
			criteria=criteria.add(Restrictions.in("groupId", ids));			
			groupUserList=criteria.list();
			for (TgroupUser groupUser : groupUserList) {
				adminIdList.add(groupUser.getAdminUser().getAdminId());
			}
			
		}
		else{
			adminIdList.add(adminId);
		}
		return adminIdList;
		
	}
	
	/**
	 * 查询与指定用户在同一组的所有用户
	 */
	@SuppressWarnings("unchecked")
	public List<TadminUser> getAllSameGroupUsers(TadminUser adminUser){
		List<TadminUser> adminUserList=new ArrayList<TadminUser>();	
		List<TgroupUser> groupUserList=groupUserDao.findBy("adminUser.adminId", adminUser.getAdminId());
		if(groupUserList!=null&&groupUserList.size()>0){
			List<Integer> ids=new ArrayList<Integer>();		
			for (TgroupUser groupUser : groupUserList) {
				ids.add(groupUser.getGroupId());
			}
			
			Criteria criteria=groupUserDao.createCriteria();
			criteria=criteria.add(Restrictions.in("groupId", ids));			
			groupUserList=criteria.list();
			for (TgroupUser groupUser : groupUserList) {
				adminUserList.add(groupUser.getAdminUser());
			}
			
		}
		else{
			adminUserList.add(adminUser);
		}
		return adminUserList;
		
	}
	
	/**
	 * 查找出所有和指定用户是一个组的所有报表管理者的帐户
	 * @param adminUser
	 * @return
	 */
	public List<TadminUser> getGroupManagerIdsByUser(TadminUser adminUser){
		
		return groupUserDao.getManagerIdsByUser(adminUser);
	}
	/**
	 * 查找出所有和指定用户是一个组的所有报表管理者的帐户
	 * @param adminUser
	 * @return
	 */
	public List<TadminUser> getGroupManagerIdsByUser(String adminUserID){
		return groupUserDao.getManagerIdsByUser(adminUserID);
	}
	
	/**
	 * 查找出所有和指定用户是一个组的报表管理者或医生的帐户
	 * @param adminUser
	 * @return
	 */
	public List<TadminUser> getGroupDoctorsByUser(String adminUserID){
		return groupUserDao.getDoctorsByUser(adminUserID);
	}

}

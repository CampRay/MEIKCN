/**   
 * @Title: UserServiceImpl.java 
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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.commons.StringTool;
import com.nuvomed.dao.GroupUserDao;
import com.nuvomed.dao.UserDao;
import com.nuvomed.dao.UserInfoDao;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.Tuser;
import com.nuvomed.dto.TuserInfo;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.AdminUserService;
import com.nuvomed.service.UserService;


/** 
 * <p>Types Description</p>
 * @ClassName: UserServiceImpl 
 * @author Phills Li 
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private GroupUserDao groupUserDao;
	@Autowired
	private AdminUserService adminUserService;
				

	/**
	 * (non-Javadoc)
	 * <p>Title: getUserById</p> 
	 * <p>Description: </p> 
	 * @param userId
	 * @return 
	 * @see com.bps.service.UserService#getUserById(java.lang.String) 
	 */
	public Tuser getUserById(int userId) {
		return userDao.get(userId);	
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: createUser</p> 
	 * <p>Description: </p> 
	 * @param User 
	 * @see com.bps.service.UserService#createUser(com.bps.dto.TUser) 
	 */
	public void createUser(Tuser User) {		
		userDao.create(User);		
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updateUser</p> 
	 * <p>Description: </p> 
	 * @param User 
	 * @see com.bps.service.UserService#updateUser(com.bps.dto.TUser) 
	 */
	public void updateUser(Tuser User) {
	
		userDao.update(User);

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: deleteUser</p> 
	 * <p>Description: </p> 
	 * @param User 
	 * @see com.bps.service.UserService#deleteUser(com.bps.dto.TUser) 
	 */
	public void deleteUser(Tuser User) {
		userDao.delete(User);
	}

	public void deleteUserById(int id) {
		userDao.deleteAll(new int[]{id});
		
	}

	public void deleteUserByIds(int[] ids) {
		userDao.deleteAll(ids);

		
	}

	public PagingData loadUserList(DataTableParamter rdtp,TadminUser adminUser) {
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			if(adminUser.getAdminRole().getRoleId()==4){
				criterionsList.add(Restrictions.eq("adminUser", adminUser));
			}
			
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					if(key=="status"){
						criterionsList.add(Restrictions.eq(key, jsonObj.getBoolean(key)));
					}
					else if(key=="Role.roleId"){
						criterionsList.add(Restrictions.eq(key, jsonObj.getInteger(key)));
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
			return userDao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return userDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}
	
   public int getUserAmount() {
		return userDao.getCount();
	}


   public void updateUserPassword(Tuser User) {
	   userDao.update(User);
	}
	   

	public Tuser getUserByEmail(String email) {
		return userDao.findUnique("email", email);
	}	
	
	public Tuser loadUserByCode(String code) {
		Criteria criteria=userDao.createCriteria();
		criteria=criteria.add(Restrictions.eq("code", code));												
		return (Tuser)criteria.uniqueResult();			
	}			
	
	@SuppressWarnings("unchecked")
	public List<Tuser> loadUserByInfo(String clientNumber,String cid,String code,String firstName,String lastName,String otherName,String birth,String email,String mobile,TadminUser adminUser) {
		Criteria criteria=userDao.createCriteria();
		if(clientNumber!=null&&!clientNumber.isEmpty()){
			try{				
			   if(StringTool.isNumber(clientNumber)){
				   TuserInfo userInfo=userInfoDao.get(Integer.parseInt(clientNumber));
				   if(userInfo!=null){
					   criteria=criteria.add(Restrictions.eq("userId", userInfo.getUserId()));					   
				   }
				   else{
					   criteria=criteria.add(Restrictions.eq("userId", 0));
				   }
			   }
			   else{
				   criteria=criteria.add(Restrictions.eq("userId", 0));
			   }
			}
			catch(Exception e){}
		}
		if(code!=null&&!code.isEmpty()){
			criteria=criteria.add(Restrictions.eq("code", code));
		}
		if(cid!=null&&!cid.isEmpty()){
			
			TadminUser searchAdminUser=adminUserService.getAdminUserByIdOrEmail(cid, cid);
			if(searchAdminUser!=null){
				criteria=criteria.add(Restrictions.eq("adminUser", searchAdminUser));
			}		
		}
		if(firstName!=null&&!firstName.isEmpty()){
			criteria=criteria.add(Restrictions.eq("firstName", firstName));
		}
		if(lastName!=null&&!lastName.isEmpty()){
			criteria=criteria.add(Restrictions.eq("lastName", lastName));
		}
		if(otherName!=null&&!otherName.isEmpty()){
			criteria=criteria.add(Restrictions.eq("otherName", otherName));
		}
		if(birth!=null&&!birth.isEmpty()){
			criteria=criteria.add(Restrictions.eq("birthday", birth));
		}
		if(email!=null&&!email.isEmpty()){
			criteria=criteria.add(Restrictions.eq("email", email));
		}
		if(mobile!=null&&!mobile.isEmpty()){
			criteria=criteria.add(Restrictions.eq("mobile", mobile));
		}
		if(adminUser!=null){
			//如果当前登录的用户角色是admin, Operator或Doctor
			if(adminUser.getAdminRole().getRoleId()<=3){
				List<String> adminIdList= groupUserDao.getAdminIdsByUser(adminUser);	
				if(adminIdList.size()>0){
					criteria=criteria.add(Restrictions.in("createdBy", adminIdList));
				}
				else{
					criteria=criteria.add(Restrictions.eq("createdBy", ""));
				}
			}
			else{//如果是普通用户
				criteria=criteria.add(Restrictions.eq("adminUser", adminUser));
			}
		}
		
		return criteria.list();			
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<Tuser> loadUserListByCids(List<TadminUser> cidList) {
		Criteria criteria=userDao.createCriteria();
		criteria=criteria.add(Restrictions.in("adminUser", cidList));												
		return criteria.list();			
	}	
	
	/**
	 * 根據用戶信息搜索同一用戶帳號下的所有用戶數據
	 * @param clientNumber
	 * @param cid
	 * @param code
	 * @param firstName
	 * @param lastName
	 * @param otherName
	 * @param birth
	 * @param email
	 * @param mobile
	 * @param adminUser
	 * @return
	 */
	public List<Tuser> loadAllUserByInfo(String clientNumber,String cid,String code,String firstName,String lastName,String otherName,String birth,String email,String mobile,TadminUser adminUser) {
		List<Tuser> userList=loadUserByInfo(clientNumber, cid, code, firstName, lastName, otherName, birth, email, mobile,adminUser);
		List<TadminUser> cidList=new ArrayList<TadminUser>();
		for (Tuser tuser : userList) {
			TadminUser clientId=tuser.getAdminUser();
			if(clientId!=null){
				cidList.add(clientId);
			}
		}
		if(cidList!=null&&cidList.size()>0){
			return loadUserListByCids(cidList);			
		}
		return null;
	}
			
}

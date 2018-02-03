package com.nuvomed.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.nuvomed.dao.UserInfoDao;
import com.nuvomed.dto.TuserInfo;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	private UserInfoDao userInfoDao;

	public TuserInfo getUserInfoById(int userInfoId) {
		// TODO Auto-generated method stub
		return userInfoDao.get(userInfoId);
	}
	

	public void updateUserInfo(TuserInfo userInfo) {
		// TODO Auto-generated method stub
		userInfoDao.update(userInfo);
	}

	public void createUserInfo(TuserInfo userInfo) {
		// TODO Auto-generated method stub
		userInfoDao.create(userInfo);
	}	
	
	public void deleteUserInfo(TuserInfo userInfo) {
		// TODO Auto-generated method stub
		userInfoDao.delete(userInfo);
	}
	
	public void deleteUserInfoById(int id) {
		userInfoDao.deleteAll(new int[]{id});		
	}

	public void deleteUserInfoByIds(int[] ids) {
		userInfoDao.deleteAll(ids);		
	}
	
	public TuserInfo loadUserInfoById(int id){
		return userInfoDao.get(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<TuserInfo> loadUserInfoByCreator(String creatorId){
		if(creatorId==null||creatorId.isEmpty()){
			return null;
		}
		Criteria criteria=userInfoDao.createCriteria();
		return criteria.add(Restrictions.eq("createdBy", creatorId))
				.addOrder(Order.desc("infoId")).list();		
	}
	
	public TuserInfo getUserInfoUserId(int userId) {
		// TODO Auto-generated method stub
		Criteria criteria=userInfoDao.createCriteria();
		return (TuserInfo)criteria.add(Restrictions.eq("user.userId", userId)).uniqueResult();				
	}
	
	@SuppressWarnings("unchecked")
	public List<TuserInfo> loadUserInfo(String firstName,String lastName,String otherName,String birthday,String mobile,String email){
		Criteria criteria=userInfoDao.createCriteria();
		if(firstName!=null&&!firstName.isEmpty()){
			criteria=criteria.add(Restrictions.eq("firstName", firstName));
		}
		if(lastName!=null&&!lastName.isEmpty()){
			criteria=criteria.add(Restrictions.eq("lastName", lastName));
		}
		if(otherName!=null&&!otherName.isEmpty()){
			criteria=criteria.add(Restrictions.eq("otherName", otherName));
		}
		if(birthday!=null&&!birthday.isEmpty()){
			criteria=criteria.add(Restrictions.eq("birthday", birthday));
		}
		if(mobile!=null&&!mobile.isEmpty()){
			criteria=criteria.add(Restrictions.eq("mobile", mobile));
		}
		if(email!=null&&!email.isEmpty()){
			criteria=criteria.add(Restrictions.eq("email", email));
		}
		criteria=criteria.add(Restrictions.isNull("user"));
		return criteria.list();
				
	}
	
	public PagingData loadUserInfoList(DataTableParamter rdtp) {
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("MM/dd/yyyy");
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);						
			boolean noParam=false;
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					noParam=true;
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
					else if(key=="infoId"){
						criterionsList.add(Restrictions.eq("infoId", Integer.parseInt(val)));
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
			if(!noParam){
				return new PagingData();
			}
			return userInfoDao.findPage("infoId",false,criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		else{
			return new PagingData();
		}		
	}	

}

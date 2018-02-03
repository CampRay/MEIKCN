package com.nuvomed.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvomed.dao.UserDataDao;
import com.nuvomed.dto.TuserData;
import com.nuvomed.service.UserDataService;

@Service
public class UserDataServiceImpl implements UserDataService{
	@Autowired
	private UserDataDao UserDataDao;

	public TuserData getUserDataById(int UserDataId) {
		// TODO Auto-generated method sTub
		return UserDataDao.get(UserDataId);
	}
	

	public void updateUserData(TuserData UserData) {
		// TODO Auto-generated method sTub
		UserDataDao.update(UserData);
	}

	public void createUserData(TuserData UserData) {
		// TODO Auto-generated method sTub
		UserDataDao.create(UserData);
	}	
	
	@SuppressWarnings("unchecked")
	public List<TuserData> loadUserDataList(int userId) {
		Criteria criteria=UserDataDao.createCriteria();
		return criteria.add(Restrictions.eq("userId", userId)).list();						
	}
		
	public List<TuserData> loadUserScreenDataList(int userId) {
//		Criteria criteria=UserDataDao.createCriteria();
//		return criteria.add(Restrictions.eq("userId", userId))
//				.add(Restrictions.eq("dataType", 1)).list();	
		return UserDataDao.findUserDataWithoutSteam(userId,1);
				
	}
		
	public List<TuserData> loadUserReportDataList(int userId) {
//		Criteria criteria=UserDataDao.createCriteria();
//		return criteria.add(Restrictions.eq("userId", userId))
//				.add(Restrictions.eq("dataType", 2)).list();
		return UserDataDao.findUserDataWithoutSteam(userId,2);
				
	}
	
	public List<TuserData> loadSysDoctorReportDataList(int userId) {
//		Criteria criteria=UserDataDao.createCriteria();
//		return criteria.add(Restrictions.eq("userId", userId))
//				.add(Restrictions.eq("dataType", 3)).list();
		return UserDataDao.findUserDataWithoutSteam(userId,3);
				
	}
		
	public List<TuserData> loadUserReportPdfList(int userId) {
//		Criteria criteria=UserDataDao.createCriteria();
//		return criteria.add(Restrictions.eq("userId", userId))
//				.add(Restrictions.eq("dataType", 4)).list();
		return UserDataDao.findUserDataWithoutSteam(userId,4);
				
	}
	
	/**
	 * 查询指定用户医生生成的PDF报告数据
	 * @param userId
	 * @return
	 */
	public TuserData loadUserPdfReport(int userId) {
		Criteria criteria=UserDataDao.createCriteria();
		return (TuserData) criteria.add(Restrictions.eq("userId", userId))
				.add(Restrictions.eq("dataType", 4))
				.addOrder(Order.desc("dataId"))
				.uniqueResult();						
	}
	
	
	/**
	 * 查询指定用户自动生成的报告列表
	 * @param userId
	 * @return
	 */	
	public TuserData loadScreenPdfReport(int userId) {
		Criteria criteria=UserDataDao.createCriteria();
		return (TuserData) criteria.add(Restrictions.eq("userId", userId))
				.add(Restrictions.eq("dataType", 5))
				.addOrder(Order.desc("dataId"))
				.uniqueResult();		
				
	}
	
	
	/**
	 * 查询指定用户Screen上传的Zip文件
	 * @param userId
	 * @return
	 */
	public TuserData loadScreenZip(int userId) {
		Criteria criteria=UserDataDao.createCriteria();
		return (TuserData) criteria.add(Restrictions.eq("userId", userId))
				.add(Restrictions.eq("dataType", 1))
				.addOrder(Order.desc("dataId"))
				.uniqueResult();						
	}
	
	
	/**
	 * 查询指定用户医生最终上传的Zip文件
	 * @param userId
	 * @return
	 */	
	public TuserData loadDoctorZip(int userId) {
		Criteria criteria=UserDataDao.createCriteria();
		return (TuserData) criteria.add(Restrictions.eq("userId", userId))
				.add(Restrictions.or( Restrictions.eq("dataType", 3), Restrictions.eq("dataType", 2)))
				.addOrder(Order.desc("dataId"))
				.uniqueResult();		
				
	}
	
	/**
	 * 查询指定用户自动生成的csv文件
	 * @param userId
	 * @return
	 */	
	public TuserData loadCsvFile(int userId) {
		Criteria criteria=UserDataDao.createCriteria();
		return (TuserData) criteria.add(Restrictions.eq("userId", userId))
				.add(Restrictions.eq("dataType", 6))
				.addOrder(Order.desc("dataId"))
				.uniqueResult();		
				
	}
}

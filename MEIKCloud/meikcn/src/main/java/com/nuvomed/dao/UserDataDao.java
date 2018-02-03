package com.nuvomed.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nuvomed.dao.base.BaseDao;
import com.nuvomed.dto.TuserData;

/**
 * Home object for domain model class TuserData.
 * @see com.bps.dto.TuserData
 * @author Hibernate Tools
 */
@Repository
public class UserDataDao extends BaseDao<TuserData> {
	
	/**
	 * 查询出没有文件流字段的UserData数据
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TuserData> findUserDataWithoutSteam(int userId,int dataType){
		Query query = this.currentSession().createQuery("select new TuserData(ud.dataId,ud.userId,ud.fileName,ud.dataType) from TuserData ud where ud.userId=:userId and ud.dataType=:dataType");
		query.setInteger("userId", userId);
		query.setInteger("dataType", dataType);
		return query.list();
//		return this.findByHqlName("findUserData", new Integer[]{userId,dataType});		
	}	
	
	@SuppressWarnings("unchecked")
	public List<TuserData> findUserDataWithoutSteam(int userId){
		Query query = this.currentSession().createQuery("select new TuserData(ud.dataId,ud.userId,ud.fileName,ud.dataType) from TuserData ud where ud.userId= :userId");
		query.setInteger("userId", userId);
		return query.list();
//		return this.findByHqlName("findAllUserData", new Integer[]{userId});		
	}
}

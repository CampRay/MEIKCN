package com.nuvomed.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nuvomed.dao.base.BaseDao;
import com.nuvomed.dto.TadminUser;

@Repository
public class AdminUserDao extends BaseDao<TadminUser> {
	public void activateusers(String[] ids){
		for(int i=0;i<ids.length;i++){
			Query   query= currentSession().createQuery("update TadminUser set status=? where adminId= ?");
			query.setParameter(1, ids[i]);
			query.setParameter(0, true);
			query.executeUpdate();
			//currentSession().flush();   
		}
		
	}
	public void deactivateusers(String[] ids){
		for(int i=0;i<ids.length;i++){
			Query   query= currentSession().createQuery("update TadminUser set status=? where adminId= ?");
			query.setParameter(1, ids[i]);
			query.setParameter(0, false);
			query.executeUpdate();
			//currentSession().flush();   
		}
	}			
}

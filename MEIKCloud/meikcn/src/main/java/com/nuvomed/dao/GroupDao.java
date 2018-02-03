package com.nuvomed.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nuvomed.dao.base.BaseDao;
import com.nuvomed.dto.Tgroup;

@Repository
public class GroupDao extends BaseDao<Tgroup> {
	
	public void activateGroups(Integer[] ids){
		for(int i=0;i<ids.length;i++){
			Query   query= currentSession().createQuery("update Tgroup set deleted=? where id= ?");
			query.setParameter(1, ids[i]);
			query.setParameter(0, false);
			query.executeUpdate();
			//currentSession().flush();   
		}
		
	}
	public void deactivateGroups(Integer[] ids){
		for(int i=0;i<ids.length;i++){
			Query   query= currentSession().createQuery("update Tgroup set deleted=? where id= ?");
			query.setParameter(1, ids[i]);
			query.setParameter(0, true);
			query.executeUpdate();
			//currentSession().flush();   
		}
	}
				
}

package com.nuvomed.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nuvomed.dao.base.BaseDao;
import com.nuvomed.dto.TlocalizedField;
@Repository
public class LocalizedFieldDao extends BaseDao<TlocalizedField>{
	
	@SuppressWarnings("unchecked")
	public List<TlocalizedField> find(String hql, Map<String, Object> params) {
		Query query = currentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.list();
	}

	public TlocalizedField getLocalizedValue(Integer entityId, Integer languageId,String tableName, String fieldName) {
		Criteria criteria=createCriteria();
		TlocalizedField localizedField = (TlocalizedField) criteria.add(Restrictions.eq("entityId", entityId))
				.add(Restrictions.eq("tableName", tableName))
				.add(Restrictions.eq("tableField", fieldName))
				.add(Restrictions.eq("language.id", languageId))
				.uniqueResult();
		return localizedField;		
	}
	
	public void deleteAllByEntityId(Integer entityId){
		String hql = "delete from TlocalizedField where entityId=?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0,entityId);
		query.executeUpdate();
	}

}

package com.nuvomed.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.nuvomed.model.PagingData;

/**
 * Dao数据访问层的基类
 * <p>Types Description</p>
 * @ClassName: BaseDao 
 * @author Phills Li 
 *
 */
public class BaseDao<T> extends HibernateDaoSupport
{	
	
    protected Class<T> clazz;
    
    @Autowired
    public void setSessionFactoryOverride(SessionFactory sessionFactory){
    	super.setSessionFactory(sessionFactory);
    }

    @SuppressWarnings("unchecked")
	public BaseDao()
    {
       
        Type type = getClass().getGenericSuperclass();
        
        if (type instanceof ParameterizedType)
        {            
            Type[] paramTypes = ((ParameterizedType)type)
                .getActualTypeArguments();
            clazz = (Class<T>)paramTypes[0];
        }
    }

    public Serializable save(T t)
    {
        return this.getHibernateTemplate().save(t);
    }
    
    public void saveOrUpdate(T t)
    {
        this.getHibernateTemplate().saveOrUpdate(t);
    }

    public T get(Serializable id)
    {
        T t = getHibernateTemplate().get(clazz, id);
        return t;
    }

    public List<T> LoadAll()
    {
        return this.getHibernateTemplate().loadAll(clazz);
    }

    public Serializable create(T t)
    {
        return getHibernateTemplate().save(t);
    }

    public void update(T t)
    {
        getHibernateTemplate().update(t);
    }

    public void delete(T t)
    {
        getHibernateTemplate().delete(t);
    }
    
    public void delete(Serializable id)
    {
        delete(get(id));
    }

    public void deleteAll(Collection<T> list)
    {
        this.getHibernateTemplate().deleteAll(list);
    }

    public void deleteAll(final Integer[] ids)
    {
    	for (Integer id : ids) {
    		delete(id);
		}
    }
    
    public void deleteAll(final Long[] ids)
    {
    	for (Long id : ids) {
    		delete(id);
		}
    }
    
    public void deleteAll(final int[] ids)
    {
    	for (int id : ids) {
    		delete(id);
		}
    }
    
    public void deleteAll(final long[] ids)
    {
    	for (long id : ids) {
    		delete(id);
		}
    }
    
    public void deleteAll(final String[] ids)
    {
    	for (String id : ids) {
    		delete(id);
		}
    }

    public void merge(T t)
    {
        this.getHibernateTemplate().merge(t);
    }
   
    @SuppressWarnings("unchecked")
	public List<T> getAll(String orderBy, boolean isAsc)
    {
        return createCriteria(orderBy, isAsc).list();
    }

    @SuppressWarnings("unchecked")
    public T findUnique(String name, Object value)
    {
        return (T)createCriteria(Restrictions.eq(name, value)).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
   	public T findUnique(String[] popertyName, Object[] value)
    {
       Criteria criteria = currentSession().createCriteria(clazz);

       for (int i = 0; i < popertyName.length; i++ )
       {
           criteria.add(Restrictions.eq(popertyName[i], value[i]));
       }
       return (T)criteria.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
   	public T findUnique(Criteria criteria)
    {       
       return (T)criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
	public List<T> findBy(String name, Object value)
    {
        return createCriteria(Restrictions.eq(name, value)).list();
    }
    
    @SuppressWarnings("unchecked")
	public List<T> findBy(String[] popertyName, Object[] value)
    {
    	Criteria criteria = currentSession().createCriteria(clazz);

        for (int i = 0; i < popertyName.length; i++ )
        {
            criteria.add(Restrictions.eq(popertyName[i], value[i]));
        }
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
	public List<T> findBy(String propertyName, Object value, String orderBy,boolean isAsc)
    {
        return createCriteria(orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
    }
    
    public Criteria createCriteria(Criterion[] criterions)
    {
        Criteria criteria = currentSession().createCriteria(clazz);
        for (int i = 0; i < criterions.length; i++ )
        {
            criteria.add(criterions[i]);
        }
        return criteria;
    }
    
    public Criteria createCriteria()
    {
        return currentSession().createCriteria(clazz);
    }

    public Criteria createCriteria(Criterion criterion)
    {
        return createCriteria(new Criterion[] {criterion});
    }

    public Criteria createCriteria(String orderBy, boolean isAsc)
    {
        return createCriteria(orderBy, isAsc, new Criterion[] {});
    }

    public Criteria createCriteria(String orderBy, boolean isAsc,Criterion criterion)
    {
        return createCriteria(orderBy, isAsc,
            new Criterion[] {criterion});
    }

    public Criteria createCriteria(String orderBy, boolean isAsc,Criterion[] criterions)
    {
        Criteria criteria = createCriteria(criterions);
        if (isAsc)
        {
            criteria.addOrder(Order.asc(orderBy));
            //criteria.addOrder(Property.forName(orderBy).asc());
        }
        else
        {
            criteria.addOrder(Order.desc(orderBy));
        	//criteria.addOrder(Property.forName(orderBy).desc());
        }
        return criteria;
    } 
    
    public long getMaxValue(String propertyName){
    	Criteria criteria =createCriteria();  
    	Object res=criteria.setProjection(Projections.max(propertyName)).uniqueResult();
    	if(res==null){
    		return 0;
    	}
    	return (Long)res;
    	
    }
    
    public long getMinValue(String propertyName){
    	Criteria criteria =createCriteria();    	
    	Object res=criteria.setProjection(Projections.min(propertyName)).uniqueResult();
    	if(res==null){
    		return 0;
    	}
    	return (Long)res;    	
    }
    
    public Integer getMinintergerValue(String propertyName){
    	Criteria criteria =createCriteria();    	
    	Object res=criteria.setProjection(Projections.min(propertyName)).uniqueResult();
    	if(res==null){
    		return 0;
    	}
    	return (Integer)res;    	
    }
    public Integer getMaxintergerValue(String propertyName){
    	Criteria criteria =createCriteria();  
    	Object res=criteria.setProjection(Projections.max(propertyName)).uniqueResult();
    	if(res==null){
    		return 0;
    	}
    	return (Integer)res;
    	
    }    
        
	@SuppressWarnings("rawtypes")
	public List findByHqlName(String hqlName)
    {
        Query query = this.currentSession().getNamedQuery(hqlName);

        return query.list();
    }

    @SuppressWarnings("rawtypes")
	public List findByHqlName(String hqlName, Object[] params)
    {
        Query query = this.currentSession().getNamedQuery(hqlName);

        for (int i = 0; i < params.length; i++ )
        {
            query.setParameter(i, params[i]);
        }
        return query.list();
    }       

    @SuppressWarnings("rawtypes")
	public PagingData findPage(Criteria criteria, int startNo, int pageSize)
    {
        CriteriaImpl impl = (CriteriaImpl)criteria;
        Projection projection = impl.getProjection();        

        int totalCount = ((Long)criteria.setProjection(
            Projections.rowCount()).uniqueResult()).intValue();
        
        criteria.setProjection(projection);
        if (projection == null)
        {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }        

        if (totalCount == 0)
        {
            return new PagingData();
        }
        
        List list = criteria.setFirstResult(startNo).setMaxResults(pageSize).list();
        PagingData page = new PagingData(totalCount, totalCount, list.toArray());        
        return page;
    }

    public PagingData findPage(int startNo, int pageSize)
    {
        return findPage(new Criterion[] {}, startNo, pageSize);
    }

    public PagingData findPage(Criterion criterion, int startNo, int pageSize)
    {
        return findPage(createCriteria(criterion), startNo, pageSize);
    }

    public PagingData findPage(Criterion[] criterions, int startNo,int pageSize)
    {
        return findPage(createCriteria(criterions), startNo, pageSize);
    }

    public PagingData findPage(String orderBy, boolean isAsc,int startNo, int pageSize)
    {
        return findPage(orderBy, isAsc, new Criterion[] {}, startNo,pageSize);
    }

    public PagingData findPage(String orderBy, boolean isAsc,Criterion criterion, int startNo, int pageSize)
    {
        return findPage(orderBy, isAsc, new Criterion[] {criterion},startNo, pageSize);
    }

    public PagingData findPage(String orderBy, boolean isAsc,Criterion[] criterions, int startNo, int pageSize)
    {
        return findPage(createCriteria(orderBy, isAsc, criterions),startNo, pageSize);
    }

    public PagingData findPage(final String hql, final int startNo, final int pageSize)
    {
        return findPage(hql, startNo, pageSize, new Object[] {});
    }

    public PagingData findPage(final String hql, final int startNo,
                         final int pageSize, final Object param)
    {
        return findPage(hql, startNo, pageSize, new Object[] {param});
    }

    @SuppressWarnings("rawtypes")
	public PagingData findPage(final String hql, final int startNo,
                         final int pageSize, final Object[] params)
    {
        String countHql = getCountHql(hql);
        Query countQuery = currentSession().createQuery(countHql);
        Query query = currentSession().createQuery(hql);
        for (int i = 0; i < params.length; i++ )
        {
            countQuery.setParameter(i, params[i]);
            query.setParameter(i, params[i]);
        }
        int totalCount = ((Long)countQuery.iterate().next()).intValue();
        if (totalCount == 0)
        {
            return new PagingData();
        }        
        List list = query.setFirstResult(startNo).setMaxResults(pageSize).list();
        PagingData page = new PagingData(totalCount, totalCount, list.toArray());        
        return page;
    }
    
    

    public PagingData findPageByNamedQuery(final String queryName, final int startNo,
                                     final int pageSize)
    {
        return findPageByNamedQuery(queryName, startNo, pageSize,
            new Object[] {});
    }

    public PagingData findPageByNamedQuery(final String queryName, final int startNo,
                                     final int pageSize, Object param)
    {
        return findPageByNamedQuery(queryName, startNo, pageSize,
            new Object[] {param});
    }

    public PagingData findPageByNamedQuery(final String queryName, final int startNo,
                                     final int pageSize, Object[] params)
    {
        String hql = currentSession().getNamedQuery(queryName).getQueryString();
        return findPage(hql, startNo, pageSize, params);
    }
    
    public int getCount(){
    	Criteria criteria = createCriteria();
    	return ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    
    protected String getCountHql(String hql)
    {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
            Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find())
        {
            m.appendReplacement(sb, "");
        }
        hql = m.appendTail(sb).toString();
        int pos = hql.toLowerCase().indexOf("from");
        if (pos == -1)
        {
            throw new RuntimeException("Invalid hql for findPage: " + hql);
        }
        hql = hql.substring(pos);
        String countHql = " select count(*) " + hql;
        return countHql;
    }
    
}

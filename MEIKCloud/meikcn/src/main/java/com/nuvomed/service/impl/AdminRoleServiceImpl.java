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

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.dao.AdminRoleDao;
import com.nuvomed.dto.TadminRole;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.AdminRoleService;

/** 
 * <p>Types Description</p>
 * @ClassName: AdminUserServiceImpl 
 * @author Phills Li 
 * 
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {
	@Autowired
	private AdminRoleDao adminRoleDao;
		
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAdminRoleById</p> 
	 * <p>Description: </p> 
	 * @param userId
	 * @return TadminRole
	 * @see com.bps.service.AdminRoleService#getAdminUserById(int) 
	 */
	public TadminRole getAdminRoleById(int roleId) {
		return adminRoleDao.get(roleId);
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAllAdminRoles</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.bps.service.AdminRoleService#getAllAdminRoles()
	 */
	@SuppressWarnings("unchecked")
	public List<TadminRole> getAllAdminRoles(){
		Criteria criteria=adminRoleDao.createCriteria();
		criteria.add(Restrictions.ne("roleId", new Integer(1)));
		criteria.add(Restrictions.ne("roleId", new Integer(5)));
		criteria.add(Restrictions.eq("status", true));
		return criteria.list();	
	}
	
	/**
	 * 加载报告工作流程相关角色
	 * <p>Title: getAllAdminRoles</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.bps.service.AdminRoleService#getAllWorkRoles()
	 */
	@SuppressWarnings("unchecked")
	public List<TadminRole> getAllWorkRoles(){
		Criteria criteria=adminRoleDao.createCriteria();
		criteria.add(Restrictions.or(Restrictions.eq("roleId", new Integer(2)),Restrictions.eq("roleId", new Integer(3))));
		criteria.add(Restrictions.eq("status", true));
		return criteria.list();		
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: createAdminRole</p> 
	 * <p>Description: </p> 
	 * @param adminRole 
	 * @see com.bps.service.AdminRoleService#createAdminUser(com.bps.dto.TadminUser) 
	 */
	public void createAdminRole(TadminRole adminRole) {
		adminRoleDao.create(adminRole);
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updateAdminRole</p> 
	 * <p>Description: </p> 
	 * @param adminRole 
	 * @see com.bps.service.AdminRoleService#updateAdminUser(com.bps.dto.TadminUser) 
	 */
	public void updateAdminRole(TadminRole adminRole) {
		adminRoleDao.update(adminRole);

	}	
	
	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminRole</p> 
	 * <p>Description: </p> 
	 * @param adminNodes 
	 * @see com.bps.service.AdminRoleService#deleteAdminRole(com.bps.dto.TadminNodes) 
	 */
	public void deleteAdminRole(TadminRole adminRole) {
		adminRoleDao.delete(adminRole);		
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminRoleById</p> 
	 * <p>Description: </p> 
	 * @param id 
	 * @see com.bps.service.AdminRoleService#deleteAdminRoleById(int)
	 */
	public void deleteAdminRoleById(int id){
		adminRoleDao.delete(id);
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminRolesByIds</p> 
	 * <p>Description: </p> 
	 * @param ids 
	 * @see com.bps.service.AdminRoleService#deleteAdminNodesByIds(int[])
	 */
	public void deleteAdminRolesByIds(Integer[] ids){		
		adminRoleDao.deleteAll(ids);
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: loadAdminRolesList</p> 
	 * <p>Description: </p> 
	 * @param rdtp
	 * @return 
	 * @see com.bps.service.AdminRoleService#loadAdminRolesList(com.bps.model.DataTableParamter)
	 */
	public PagingData loadAdminRolesList(DataTableParamter rdtp){
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					if(key=="status"){
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
			return adminRoleDao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return adminRoleDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

}

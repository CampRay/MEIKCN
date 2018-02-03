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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvomed.dao.AdminRoleRightsDao;
import com.nuvomed.dto.TadminRoleRights;
import com.nuvomed.service.AdminRoleRightsService;

/** 
 * <p>Types Description</p>
 * @ClassName: AdminUserServiceImpl 
 * @author Phills Li 
 * 
 */
@Service
public class AdminRoleRightsServiceImpl implements AdminRoleRightsService {
	@Autowired
	private AdminRoleRightsDao adminRoleRightsDao;
		
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAdminRoleRightsById</p> 
	 * <p>Description: </p> 
	 * @param roleId
	 * @return TadminRoleRights
	 * @see com.bps.service.AdminRoleRightsService#getAdminRoleRightsById(int)
	 */
	public TadminRoleRights getAdminRoleRightsById(int roleId) {
		return adminRoleRightsDao.get(roleId);
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: saveAdminRoleRights</p> 
	 * <p>Description: </p> 
	 * @param adminRoleRights 
	 * @see com.bps.service.AdminRoleRightsService#createAdminRoleRights(com.bps.dto.TadminRoleRights)
	 */
	public void saveAdminRoleRights(TadminRoleRights adminRoleRights) {
		adminRoleRightsDao.saveOrUpdate(adminRoleRights);
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: createAdminRoleRights</p> 
	 * <p>Description: </p> 
	 * @param adminRoleRights 
	 * @see com.bps.service.AdminRoleRightsService#createAdminRoleRights(com.bps.dto.TadminRoleRights)
	 */
	public void createAdminRoleRights(TadminRoleRights adminRoleRights) {
		adminRoleRightsDao.create(adminRoleRights);
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updateAdminRoleRights</p> 
	 * <p>Description: </p> 
	 * @param adminRoleRights 
	 * @see com.bps.service.AdminRoleRightsService#updateAdminRoleRights(com.bps.dto.TadminRoleRights)
	 */
	public void updateAdminRoleRights(TadminRoleRights adminRoleRights) {
		adminRoleRightsDao.update(adminRoleRights);

	}	

	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminRoleRights</p> 
	 * <p>Description: </p> 
	 * @param adminRoleRights 
	 * @see com.bps.service.AdminRoleRightsService#deleteAdminRoleRights(com.bps.dto.TadminRoleRights)
	 */
	public void deleteAdminRoleRights(TadminRoleRights adminRoleRights) {
		adminRoleRightsDao.delete(adminRoleRights);
	}	

}

/**   
 * @Title: AdminNodesServiceImpl.java 
 * @Package com.bps.service.impl 
 *
 * @Description: User Points Management System
 * 
 * @date Nov 1, 2014 12:57:59 PM
 * @version V1.0   
 */ 
package com.nuvomed.service.impl;

import java.util.ArrayList;
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
import com.nuvomed.commons.MyException;
import com.nuvomed.commons.SystemConfig;
import com.nuvomed.dao.AdminNodesDao;
import com.nuvomed.dto.TadminNodes;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.AdminNodesService;

/** 
 * <p>Types Description</p>
 * @ClassName: AdminNodesServiceImpl 
 * @author Phills Li 
 * 
 */
@Service
public class AdminNodesServiceImpl implements AdminNodesService {	

	@Autowired
	private AdminNodesDao adminNodesDao;		
		

	/**
	 * (non-Javadoc)
	 * <p>Title: getAdminNodeById</p> 
	 * <p>Description: </p> 
	 * @param nodeId
	 * @return 
	 * @see com.bps.service.AdminNodesService#getAdminNodeById(int) 
	 */
	public TadminNodes getAdminNodeById(int nodeId) {		
		return adminNodesDao.get(nodeId);
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAllAdminNodes</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.bps.service.AdminNodesService#getAllAdminNodes()
	 */
	public List<TadminNodes> getAllAdminNodes(){
		return adminNodesDao.LoadAll();
	}
	
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAllAdminNodes</p> 
	 * <p>Description: </p> 
	 * @return List<TadminNodes>
	 * @see com.bps.service.AdminNodesService#getAllAdminNodes()
	 */
	@SuppressWarnings("unchecked")
	public List<TadminNodes> getAllEnabledAdminNodes(){
		Criteria criteria=adminNodesDao.createCriteria();
		return criteria.add(Restrictions.eq("status", true))
				.addOrder(Order.asc("groupSort"))
				.list();			
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAllAdminNodesMenu</p> 
	 * <p>Description: </p> 
	 * @return List<TadminNodes>
	 * @see com.bps.service.AdminNodesService#getAllAdminNodesMenu()
	 */
	@SuppressWarnings("unchecked")
	public List<TadminNodes> getAllAdminNodesMenu(){
		Criteria criteria=adminNodesDao.createCriteria();
		return criteria.add(Restrictions.eq("isMenu", true))
				.add(Restrictions.eq("status", true))
				.addOrder(Order.asc("groupSort"))
				.list();						
	}		
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAdminNodesMenuByPid</p> 
	 * <p>Description: </p> 
	 * @return List<TadminNodes>
	 * @see com.bps.service.AdminNodesService#getAdminNodesMenuByPid()
	 */
	@SuppressWarnings("unchecked")
	public List<TadminNodes> getAdminNodesMenuByPid(Integer pid){
		Criteria criteria=adminNodesDao.createCriteria();
		return criteria.add(Restrictions.eq("isMenu", true))
				.add(Restrictions.eq("status", true))
				.add(Restrictions.eq("pid", pid))
				.addOrder(Order.asc("groupSort"))
				.list();						
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: createAdminNode</p> 
	 * <p>Description: </p> 
	 * @param adminNodes 
	 * @see com.bps.service.AdminNodesService#createAdminNode(com.bps.dto.TadminNodes) 
	 */
	public void createAdminNode(TadminNodes adminNode) {
		if(adminNode.getPid()!=0&&adminNodesDao.get(adminNode.getPid())==null){
			throw new MyException("error.AdminNodesServiceImpl.createAdminNode.pid");
		}
		long bitval=adminNodesDao.getMaxValue("bitFlag");
		long nodeValue=1;
		if(bitval>0){
			nodeValue=bitval*2;
		}		
		adminNode.setBitFlag(nodeValue);
		adminNodesDao.create(adminNode);	
		cachedNodesData();
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updateAdminRole</p> 
	 * <p>Description: </p> 
	 * @param adminNodes 
	 * @see com.bps.service.AdminNodesService#updateAdminNode(com.bps.dto.TadminNodes) 
	 */
	public void updateAdminNode(TadminNodes adminNode) {
		if(adminNode.getPid()!=0&&adminNodesDao.get(adminNode.getPid())==null){
			throw new MyException("error.AdminNodesServiceImpl.createAdminNode.pid");
		}
		adminNodesDao.update(adminNode);
		cachedNodesData();
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminNode</p> 
	 * <p>Description: </p> 
	 * @param adminNodes 
	 * @see com.bps.service.AdminNodesService#deleteAdminNode(com.bps.dto.TadminNodes) 
	 */
	public void deleteAdminNode(TadminNodes adminNode) {
		adminNodesDao.delete(adminNode);
		cachedNodesData();
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminNodeById</p> 
	 * <p>Description: </p> 
	 * @param id 
	 * @see com.bps.service.AdminNodesService#deleteAdminNodeById(int)
	 */
	public void deleteAdminNodeById(int id){
		adminNodesDao.delete(id);
		cachedNodesData();
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminNodesByIds</p> 
	 * <p>Description: </p> 
	 * @param ids 
	 * @see com.bps.service.AdminNodesService#deleteAdminNodesByIds(int[])
	 */
	public void deleteAdminNodesByIds(Integer[] ids){		
		adminNodesDao.deleteAll(ids);
		cachedNodesData();
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: loadAdminNodesList</p> 
	 * <p>Description: </p> 
	 * @param rdtp
	 * @return 
	 * @see com.bps.service.AdminNodesService#loadAdminNodesList(com.bps.model.DataTableParamter)
	 */
	public PagingData loadAdminNodesList(DataTableParamter rdtp){
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					if(key=="isMenu"||key=="status"){
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
			return adminNodesDao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return adminNodesDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

	/**
	 * <p>Title: Cache all the nodes data</p> 
	 * <p>Description: </p>  
	 * @see com.bps.service.AdminNodesService#cachedNodesData()
	 */
	public void cachedNodesData(){
		List<TadminNodes> nodesList=getAllEnabledAdminNodes();
		SystemConfig.Admin_Nodes_Url_Map.clear();
		SystemConfig.Admin_Nodes_Menu_Map.clear();
		SystemConfig.Admin_Nodes_Group_Map.clear();
		for (TadminNodes adminNodes : nodesList) {    				
			SystemConfig.Admin_Nodes_Url_Map.put(adminNodes.getMethod()+"@"+adminNodes.getUri(), adminNodes.getBitFlag());
			    
			//Build menu mapping
			if(adminNodes.isIsMenu()&&adminNodes.getPid()==0){
				List<TadminNodes> menuList=getAdminNodesMenuByPid(adminNodes.getNodeId());
				SystemConfig.Admin_Nodes_Menu_Map.put(adminNodes, menuList);   	    					
			}
			
			//Build group nodes mapping
			String groupName=adminNodes.getGroupName();
			List<TadminNodes> groupList=SystemConfig.Admin_Nodes_Group_Map.get(groupName);
			if(groupList==null){
				groupList=new ArrayList<TadminNodes>();
				SystemConfig.Admin_Nodes_Group_Map.put(groupName, groupList);
			}    				
			groupList.add(adminNodes);
		}
	}
	
}

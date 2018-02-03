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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuvomed.dao.RecordsDao;
import com.nuvomed.dto.Trecords;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.RecordsService;

/** 
 * <p>Types Description</p>
 * @ClassName: VersionImpl 
 * @author Phills Li 
 * 
 */
@Service
public class RecordsServiceImpl implements RecordsService {
	
	@Autowired
	private RecordsDao recordsDao;

	public Trecords getRecordById(int id) {		
		return recordsDao.get(id);
	}

	public void createRecord(Trecords record) {
		recordsDao.create(record);
	}

	public void updateRecord(Trecords record) {
		recordsDao.update(record);
		
	}	

	public void deleteRecord(Trecords record) {
		recordsDao.delete(record);
		
	}

	public void deleteRecordById(Integer id) {
		recordsDao.delete(id);
	}

	public void deleteRecordsByIds(Integer[] ids) {
		recordsDao.deleteAll(ids);
	}

	public PagingData loadRecordsList(DataTableParamter rdtp) {
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("MM/dd/yyyy");
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					 if(key=="startTime"){
						Date sdate = null;
						try {
							sdate = simpleDateFormat.parse(val);							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Long startLong=sdate.getTime();						
						criterionsList.add(Restrictions.ge("screenTime", startLong));
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
						criterionsList.add(Restrictions.le("screenTime", endLong));
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
			return recordsDao.findPage("screenTime",false,criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return recordsDao.findPage("screenTime",false,rdtp.iDisplayStart, rdtp.iDisplayLength);
	}		
	
}

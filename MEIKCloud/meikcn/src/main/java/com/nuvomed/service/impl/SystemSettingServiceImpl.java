package com.nuvomed.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvomed.commons.SystemConfig;
import com.nuvomed.dao.SettingDao;
import com.nuvomed.dto.Tsetting;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.SystemSettingService;
@Service
public class SystemSettingServiceImpl implements SystemSettingService {

	
	@Autowired
	private SettingDao settingDao;

	
	public Tsetting getSystemsettingById(Integer Id) {
		return settingDao.get(Id);
	}

	
	public void createSystemsetting(Tsetting setting) {
		settingDao.create(setting);
	}

	
	public void updateSystemsetting(Tsetting setting) {
		settingDao.update(setting);
	}

	
	public void deleteSystemsetting(Tsetting setting) {
		settingDao.delete(setting);
	}

	
	public void deleteSystemsetting(int id) {
		settingDao.delete(id);
	}

	
	public void deleteSystemsettingByIds(Integer[] ids) {
		settingDao.deleteAll(ids);
	}

	
	public PagingData loadSystemsetting() {		
		return settingDao.findPage(0, 10);
	}


	public List<Tsetting> getAllSystemSetting() {
		return settingDao.LoadAll();
	}
	
	
	public void cachedSystemSettingData() throws IOException {
		List <Tsetting> setingList = getAllSystemSetting();		
		SystemConfig.Admin_Setting_Map.clear();				
		for(Tsetting setting:setingList){
			SystemConfig.Admin_Setting_Map.put(setting.getName(),new String(setting.getValue(),"UTF-8"));			
		}		
	}

	
	public Tsetting getSystemSettingByName(String name) {
		return settingDao.findUnique("name", name);
	}

	

}

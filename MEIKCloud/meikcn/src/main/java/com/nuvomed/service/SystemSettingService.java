package com.nuvomed.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.nuvomed.dto.Tsetting;
import com.nuvomed.model.PagingData;

public interface SystemSettingService {
	
	Tsetting getSystemsettingById(Integer Id);
	
	void createSystemsetting(Tsetting setting);
	
	void updateSystemsetting(Tsetting setting);
	
	void deleteSystemsetting(Tsetting setting);
	
	void deleteSystemsetting(int id);
	
	void deleteSystemsettingByIds(Integer[] ids);
	
	public PagingData loadSystemsetting();
	
	public List<Tsetting> getAllSystemSetting();

	public void cachedSystemSettingData() throws UnsupportedEncodingException, IOException;
		
	
	Tsetting getSystemSettingByName(String name);

}

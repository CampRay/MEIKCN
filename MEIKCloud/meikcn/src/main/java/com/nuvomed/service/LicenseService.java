package com.nuvomed.service;

import com.nuvomed.dto.Tlicense;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;

public interface LicenseService {
	
	Tlicense getLicenseById(String license);
	
	void createLicense(Tlicense license);
	
	void updateLicense(Tlicense license);	
	
	void deleteLicense(Tlicense license);
	
	void deleteLicenseById(String id);
	
	void deleteLicenseByIds(String[] ids);
	
	
	public PagingData loadLicenseList(DataTableParamter rdtp);
	
	void activeLicenseByids(String[] ids);
	
	void deactiveLicenseByids(String[] ids);
	
		
}

package com.nuvomed.service;

import java.util.List;

import com.nuvomed.dto.TuserData;

public interface UserDataService{
	TuserData getUserDataById(int userDataId);
	void updateUserData(TuserData userData);	
	void createUserData(TuserData userData);
	List<TuserData> loadUserDataList(int userId);
	List<TuserData> loadUserScreenDataList(int userId);
	List<TuserData> loadUserReportDataList(int userId);
	List<TuserData> loadSysDoctorReportDataList(int userId);
	List<TuserData> loadUserReportPdfList(int userId);
	
	TuserData loadUserPdfReport(int userId);
	TuserData loadScreenPdfReport(int userId);
	TuserData loadScreenZip(int userId);
	TuserData loadDoctorZip(int userId);
	TuserData loadCsvFile(int userId);
}

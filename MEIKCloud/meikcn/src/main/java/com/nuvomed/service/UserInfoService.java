package com.nuvomed.service;

import java.util.List;

import com.nuvomed.dto.TuserInfo;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;

public interface UserInfoService{
	TuserInfo getUserInfoById(int userInfoId);
	void updateUserInfo(TuserInfo userInfo);	
	void createUserInfo(TuserInfo userInfo);
	public TuserInfo getUserInfoUserId(int userId);
	
	public void deleteUserInfo(TuserInfo userInfo) ;
	
	public void deleteUserInfoById(int id);

	public void deleteUserInfoByIds(int[] ids);
	
	public TuserInfo loadUserInfoById(int id);
	
	public List<TuserInfo> loadUserInfoByCreator(String creatorId);
	
	public List<TuserInfo> loadUserInfo(String firstName,String lastName,String otherName,String birthday,String mobile,String email);
	
	public PagingData loadUserInfoList(DataTableParamter rdtp);
}

package com.nuvomed.service;

import com.nuvomed.dto.TadminInfo;

public interface AdminInfoService{
	TadminInfo getAdminInfoById(String adminId);
	void updateAdminInfo(TadminInfo adminInfo);
	void updateAdminInfoAvatar(TadminInfo adminInfo);
	void createAdminInfo(TadminInfo adminInfo);
}

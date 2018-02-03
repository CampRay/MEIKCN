package com.nuvomed.service;

import java.util.List;

import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.Tuser;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;

public interface UserService {
	
	Tuser getUserById(int userId);
	
	void createUser(Tuser User);
	
	void updateUser(Tuser User);
	
	void updateUserPassword(Tuser User);
	
	void deleteUser(Tuser User);
	
	void deleteUserById(int id);
	
	void deleteUserByIds(int[] ids);
	
	
	public PagingData loadUserList(DataTableParamter rdtp,TadminUser adminUser);
	
	public int getUserAmount();
	
	public Tuser getUserByEmail(String email);
	
	public Tuser loadUserByCode(String code);
	
	public List<Tuser> loadUserByInfo(String clientNumber,String cid,String code,String firstName,String lastName,String otherName,String birth,String email,String mobile,TadminUser adminUser);
	
	public List<Tuser> loadUserListByCids(List<TadminUser> cidList);
	
	public List<Tuser> loadAllUserByInfo(String clientNumber,String cid,String code,String firstName,String lastName,String otherName,String birth,String email,String mobile,TadminUser adminUser);
		
}

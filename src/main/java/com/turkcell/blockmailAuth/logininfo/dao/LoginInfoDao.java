package com.turkcell.blockmailAuth.logininfo.dao;

import com.turkcell.blockmailAuth.logininfo.model.UserInformationModel;

public interface LoginInfoDao {
	
	public UserInformationModel findByUsername(String username);
}

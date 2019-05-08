package com.turkcell.blockmailAuth.logininfo.service;

import java.net.URISyntaxException;

import com.turkcell.blockmailAuth.logininfo.model.LoginInfoOutput;

public interface LoginInfoService {
	
	public LoginInfoOutput getLoginInformation(String code) throws URISyntaxException ;
	
	public LoginInfoOutput getLoginInformationViaRefreshToken(String refreshCode) throws URISyntaxException;
}

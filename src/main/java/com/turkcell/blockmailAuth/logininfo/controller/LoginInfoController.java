package com.turkcell.blockmailAuth.logininfo.controller;

import java.net.URISyntaxException;

import com.turkcell.blockmailAuth.logininfo.model.LoginInfoInput;
import com.turkcell.blockmailAuth.logininfo.model.LoginInfoOutput;

public interface LoginInfoController {
	
	public LoginInfoOutput getLoginInformation(LoginInfoInput input) throws URISyntaxException;
	
	public String getOAuth2Code(String code);
	
	public LoginInfoOutput getLoginInformationGetRefreshToken(LoginInfoInput input) throws URISyntaxException;

}

package com.turkcell.blockmailAuth.logininfo.controller.impl;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.blockmailAuth.logininfo.controller.LoginInfoController;
import com.turkcell.blockmailAuth.logininfo.model.LoginInfoInput;
import com.turkcell.blockmailAuth.logininfo.model.LoginInfoOutput;
import com.turkcell.blockmailAuth.logininfo.service.LoginInfoService;

@RestController
public class LoginInfoControllerImpl implements LoginInfoController {
	
	@Autowired
	private LoginInfoService loginInfoService;
	
	
	@Override
	@RequestMapping(value = "/getLoginInformation")
	public LoginInfoOutput getLoginInformation(@RequestBody LoginInfoInput input) throws URISyntaxException {
		return loginInfoService.getLoginInformation(input.getCode());
	}


	@Override
	@RequestMapping(value = "/getAuthCode")
	public String getOAuth2Code(@RequestParam("code") String code) {
		return code;
	}


	@Override
	@RequestMapping(value = "/getLoginInformationForRT")
	public LoginInfoOutput getLoginInformationGetRefreshToken(@RequestBody LoginInfoInput input) throws URISyntaxException {
		return loginInfoService.getLoginInformationViaRefreshToken(input.getCode());
	}

}

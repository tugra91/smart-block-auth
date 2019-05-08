package com.turkcell.blockmailAuth.logininfo.model;

import java.io.Serializable;
import java.util.Map;

public class LoginInfoOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4168003827768783246L;
	
	private String accessToken;
	private String refreshToken;
	private Map<String, Object> additionalInformation;
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public Map<String, Object> getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(Map<String, Object> additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	
	@Override
	public String toString() {
		return "LoginInfoOutput [accessToken=" + accessToken + ", refreshToken=" + refreshToken
				+ ", additionalInformation=" + additionalInformation + "]";
	}
	
	
	
	

}

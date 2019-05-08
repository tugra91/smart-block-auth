package com.turkcell.blockmailAuth.logininfo.model;

import java.io.Serializable;

public class LoginInfoInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3226669659624868863L;
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "LoginInfoInput [code=" + code + "]";
	}
	
	

}

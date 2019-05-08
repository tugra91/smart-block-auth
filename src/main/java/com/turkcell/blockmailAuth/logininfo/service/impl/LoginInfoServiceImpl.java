package com.turkcell.blockmailAuth.logininfo.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.turkcell.blockmailAuth.logininfo.model.LoginInfoOutput;
import com.turkcell.blockmailAuth.logininfo.service.LoginInfoService;

@Service
public class LoginInfoServiceImpl implements LoginInfoService {
	
	@Autowired
	@Qualifier("restTemplateBean")
	private RestTemplate restTemplate;
	
	private static final String OAuth2URL = "http://localhost:8090/oauth/token";
	private static final String CLIENT_ID = "blockMail";
	private static final String GRANT_TYPE = "authorization_code";
	private static final String REDIRECT_URI = "http://localhost:8090/getAuthCode";
	private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

	@Override
	public LoginInfoOutput getLoginInformation(String code) throws URISyntaxException {
		LoginInfoOutput output = new LoginInfoOutput();
		
		
		URI url = new URI(OAuth2URL);
		HttpHeaders body = new HttpHeaders();
		body.add("client_id", CLIENT_ID);
		body.add("redirect_uri",REDIRECT_URI);
		body.add("code", code);
		body.add("grant_type", GRANT_TYPE);

		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, createHeaders("blockMail", "123"));
		
		ResponseEntity<OAuth2AccessToken> response = 
				restTemplate.exchange(url, HttpMethod.POST, entity, OAuth2AccessToken.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			output.setAccessToken(response.getBody().getValue());
			output.setRefreshToken(response.getBody().getRefreshToken().getValue());
			output.setAdditionalInformation(response.getBody().getAdditionalInformation());
		}
		
		return output;
		
		
	}
	
	@Override
	public LoginInfoOutput getLoginInformationViaRefreshToken(String refreshCode) throws URISyntaxException {
		LoginInfoOutput output = new LoginInfoOutput();
		
		URI url = new URI(OAuth2URL);
		HttpHeaders body = new HttpHeaders();
		body.add("refresh_token", refreshCode);
		body.add("grant_type", GRANT_TYPE_REFRESH_TOKEN);

		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, createHeaders("blockMail", "123"));
		
		ResponseEntity<OAuth2AccessToken> response = 
				restTemplate.exchange(url, HttpMethod.POST, entity, OAuth2AccessToken.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			output.setAccessToken(response.getBody().getValue());
			output.setRefreshToken(response.getBody().getRefreshToken().getValue());
			output.setAdditionalInformation(response.getBody().getAdditionalInformation());
		}
		
		return output;
	}
	
	
	private HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {/**
			 * 
			 */
			private static final long serialVersionUID = 8998352702764652594L;
		/**
			 * 
			 */

		{
	         String auth = username + ":" + password;
	         byte[] encodedAuth = Base64.getEncoder().encode( 
	            auth.getBytes(Charset.forName("UTF-8")) );
	         String authHeader = "Basic " + new String( encodedAuth );
	         set( "Authorization", authHeader );
	         set("Content-Type", "application/x-www-form-urlencoded");
	      }};
	}


	

}

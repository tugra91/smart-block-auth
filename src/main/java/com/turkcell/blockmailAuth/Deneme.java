package com.turkcell.blockmailAuth;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Deneme {
	
	@Autowired
	@Qualifier("restTemplateBean")
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/deneme2")
	public String deneme(@RequestParam("code") String code) throws URISyntaxException {
		
		URI url = new URI("http://localhost:8090/oauth/token");
		HttpHeaders body = new HttpHeaders();
		body.add("client_id", "blockMail");
		body.add("redirect_uri","http://localhost:8090/deneme");
		body.add("code", code);
		body.add("grant_type", "authorization_code");

		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, createHeaders("blockMail", "123"));
		
		ResponseEntity<OAuth2AccessToken> response = 
				restTemplate.exchange(url, HttpMethod.POST, entity, OAuth2AccessToken.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			System.out.println(response.getBody());
		}
		
		return code;
	}
	
	@RequestMapping(value = "/admin")
	public String admin(HttpServletRequest principal) {
		return "Hoşgeldin Babuş";
	}
	
	@RequestMapping(value = "/deneme")
	public String deneme1(@RequestParam("code") String code) throws URISyntaxException {
		return code;
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
	
	
	public static void main (String [] args) {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		encode.encode("0000");
	}
}

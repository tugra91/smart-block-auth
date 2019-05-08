package com.turkcell.blockmailAuth.config.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.turkcell.blockmailAuth.logininfo.dao.LoginInfoDao;
import com.turkcell.blockmailAuth.logininfo.model.UserInformationModel;

@Component
public class OAuth2UserDetailsComponent implements UserDetailsService {
	
	@Autowired
	private LoginInfoDao loginInfoDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInformationModel userInformation = loginInfoDao.findByUsername(username);
		
		if(userInformation == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		
		if(userInformation != null && !userInformation.isApproved()) {
			throw new UsernameNotFoundException("User Not Approved");
		}
		
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(String rs: userInformation.getRoles()) {
			SimpleGrantedAuthority authRole = new SimpleGrantedAuthority(rs);
			authorities.add(authRole);
		}
		
		return new User(userInformation.getUsername(), userInformation.getPassword(), authorities);
		
	}
	
	

}

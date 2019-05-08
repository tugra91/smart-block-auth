package com.turkcell.blockmailAuth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.turkcell.blockmailAuth.config.component.OAuth2UserDetailsComponent;

@Configuration
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Qualifier("passwordEncoder")
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private OAuth2UserDetailsComponent userDetailsComponent;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/oauth/token","/oauth/authorize").permitAll();
		http.httpBasic();
		http.csrf().disable();

	}


	@Override
	protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		
		authBuilder.userDetailsService(userDetailsComponent);

		//		String password = getPasswordEncoder().encode("admin");
//		authBuilder.inMemoryAuthentication().passwordEncoder(getPasswordEncoder())
//		.withUser("admin")
//		.password(password)
//		.roles("ADMIN");
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}






}

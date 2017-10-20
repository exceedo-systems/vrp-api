package com.exceedo.vrpapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.exceedo.vrpapi.service.ApiClientUserDetailsService;

@Configuration
@EnableWebSecurity
public class VrpApiSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private VrpApiAuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	private ApiClientUserDetailsService userDataService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.anyRequest().authenticated()
				.and().httpBasic()
				.authenticationEntryPoint(authEntryPoint);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDataService);
	}

}
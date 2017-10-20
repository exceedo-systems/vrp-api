package com.exceedo.vrpapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exceedo.vrpapi.domain.ApiClient;
import com.exceedo.vrpapi.repository.ApiClientRepository;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service
public class ApiClientUserDetailsService implements UserDetailsService {

	@Autowired
	private ApiClientRepository apiClientRepository;
	
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      ApiClient apiClient = apiClientRepository.findOne(username);
      if(apiClient != null) {
      return new User(apiClient.getUserName(), apiClient.getPassword(), true, true, true, true,
              AuthorityUtils.createAuthorityList("USER"));
      } else {
        throw new UsernameNotFoundException("could not find the user '"
                + username + "'");
      }
    }
}

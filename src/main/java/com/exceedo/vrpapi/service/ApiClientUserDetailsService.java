package com.exceedo.vrpapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exceedo.vrpapi.domain.ApiUser;
import com.exceedo.vrpapi.repository.ApiUserRepository;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service
public class ApiClientUserDetailsService implements UserDetailsService {

	@Autowired
	private ApiUserRepository apiUserRepository;
	
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      ApiUser apiUser = apiUserRepository.findOne(username);
      if(apiUser != null) {
      return new User(apiUser.getUserName(), apiUser.getPassword(), true, true, true, true,
              AuthorityUtils.createAuthorityList("USER"));
      } else {
        throw new UsernameNotFoundException("could not find the user '"
                + username + "'");
      }
    }
}

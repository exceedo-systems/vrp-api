package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.ApiUser;

public interface ApiUserRepository extends MongoRepository<ApiUser, String>  {

}


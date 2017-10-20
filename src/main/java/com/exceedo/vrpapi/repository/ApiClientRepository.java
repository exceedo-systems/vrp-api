package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.ApiClient;

public interface ApiClientRepository extends MongoRepository<ApiClient, String>  {

}


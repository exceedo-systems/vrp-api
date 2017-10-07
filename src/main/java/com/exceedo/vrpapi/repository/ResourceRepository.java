package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.Resource;

public interface ResourceRepository extends MongoRepository<Resource, Integer>  {

}

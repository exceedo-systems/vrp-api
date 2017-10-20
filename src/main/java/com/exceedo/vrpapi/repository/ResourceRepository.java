package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.ResourceList;

public interface ResourceRepository extends MongoRepository<ResourceList, String>  {

}

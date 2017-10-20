package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.RouteTypeList;

public interface RouteTypeRepository extends MongoRepository<RouteTypeList, String>  {

}

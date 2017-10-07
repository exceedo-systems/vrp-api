package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.RouteType;

public interface RouteTypeRepository extends MongoRepository<RouteType, Integer>  {

}

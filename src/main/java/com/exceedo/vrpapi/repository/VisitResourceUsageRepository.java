package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.VisitResourceUsageList;

public interface VisitResourceUsageRepository extends MongoRepository<VisitResourceUsageList, String>  {

}


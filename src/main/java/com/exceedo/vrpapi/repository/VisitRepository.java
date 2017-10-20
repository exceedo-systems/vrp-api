package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.VisitList;

public interface VisitRepository extends MongoRepository<VisitList, String>  {

}

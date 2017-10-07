package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.Visit;

public interface VisitRepository extends MongoRepository<Visit, Integer>  {

}

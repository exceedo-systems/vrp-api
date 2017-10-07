package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.Link;

public interface LinkRepository extends MongoRepository<Link, Integer>  {

}


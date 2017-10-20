package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.LinkList;

public interface LinkRepository extends MongoRepository<LinkList, String>  {

}


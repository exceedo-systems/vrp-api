package com.exceedo.vrpapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exceedo.vrpapi.domain.LinkResourceUsageList;

public interface LinkResourceUsageRepository extends MongoRepository<LinkResourceUsageList, String>  {

}


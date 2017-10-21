package com.exceedo.vrpapi.controller;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exceedo.vrpapi.domain.ApiUser;
import com.exceedo.vrpapi.domain.VrpJob;
import com.exceedo.vrpapi.repository.ApiUserRepository;


@RestController
public class ApiUserController {

	@Autowired
	private ApiUserRepository apiUserRepository;

	@RequestMapping(path = "admin/apiuser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse createApiUser(@RequestBody ApiUser apiUser) {

		DataSaveResponse response = new DataSaveResponse();
		String apiKey = UUID.randomUUID().toString();
		apiUser.setApiKey(apiKey);
		apiUser.setJobCount(0);
		apiUser.setJobs(new ArrayList<VrpJob>());
		apiUserRepository.save(apiUser);
		response.setSaveCount(1);
		response.setStatus("success");
		return response;
	}

}
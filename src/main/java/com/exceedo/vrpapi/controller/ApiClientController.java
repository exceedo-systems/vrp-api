package com.exceedo.vrpapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exceedo.vrpapi.domain.ApiClient;
import com.exceedo.vrpapi.repository.ApiClientRepository;


@RestController
public class ApiClientController {

	@Autowired
	private ApiClientRepository apiClientRepository;

	@RequestMapping(path = "admin/apiclient", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse createApiClient(@RequestBody ApiClient apiClient) {

		DataSaveResponse response = new DataSaveResponse();
		apiClientRepository.save(apiClient);
		response.setSaveCount(1);
		response.setStatus("success");
		return response;
	}

}
package com.exceedo.vrpapi.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exceedo.vrpapi.domain.ApiClient;
import com.exceedo.vrpapi.domain.VrpJob;
import com.exceedo.vrpapi.domain.VrpJobStatus;
import com.exceedo.vrpapi.excepion.VrpJobException;
import com.exceedo.vrpapi.repository.ApiClientRepository;

@RestController
public class VrpJobController {
	
	@Autowired
	private ApiClientRepository apiClientRepository;
	
	@RequestMapping(path = "/job", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse createVrpJob(HttpServletRequest request, @RequestBody VrpJob job) throws VrpJobException {

		DataSaveResponse response = new DataSaveResponse();
		Principal principal = ((HttpServletRequest) request).getUserPrincipal();
		ApiClient client = apiClientRepository.findOne(principal.getName());
		List<VrpJob> jobs =  client.getJobs();
		Stream<VrpJob> jobStream = jobs.stream();
		boolean jobExists = jobStream.anyMatch(j -> j.getName().equalsIgnoreCase(job.getName()));
		if(jobExists) {
			throw new VrpJobException("Job Exists with given Name");
		}
		
		String jobId = UUID.randomUUID().toString();
		job.setId(jobId);
		job.setStatus(VrpJobStatus.CREATED);
		jobs.add(job);
		int jobCount = client.getJobCount();
		client.setJobCount(jobCount + 1);
		apiClientRepository.save(client);
		response.setSaveCount(1);
		response.setStatus("success, total jobs created " + (jobCount + 1));
		return response;
	}
	
	@RequestMapping(path = "/jobs", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public List<VrpJob> getVrpJobs(HttpServletRequest request){
		
		Principal principal = ((HttpServletRequest) request).getUserPrincipal();
		ApiClient client = apiClientRepository.findOne(principal.getName());
		List<VrpJob> jobs =  client.getJobs();
		return jobs;
	}
	
	@RequestMapping(path = "/job/{jobId}/validate", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public VrpJobValidationResponse validateVrpJob(HttpServletRequest request, @PathVariable final String jobId){
		VrpJobValidationResponse response = new VrpJobValidationResponse();
		Principal principal = ((HttpServletRequest) request).getUserPrincipal();
		ApiClient client = apiClientRepository.findOne(principal.getName());
		List<VrpJob> jobs =  client.getJobs();
		VrpJob job = jobs.stream().filter(j -> j.getId().equals(jobId)).findFirst().orElse(null);
		response.setJobId(jobId);
		response.setJobName(job.getName());
		response.setStatus(VrpJobStatus.VALIDATED.name());
		return response;
	}

}

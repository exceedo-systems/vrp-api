package com.exceedo.vrpapi.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exceedo.vrpapi.domain.ApiUser;
import com.exceedo.vrpapi.domain.LinkList;
import com.exceedo.vrpapi.domain.LinkResourceUsageList;
import com.exceedo.vrpapi.domain.ResourceList;
import com.exceedo.vrpapi.domain.RouteTypeList;
import com.exceedo.vrpapi.domain.VisitList;
import com.exceedo.vrpapi.domain.VrpJob;
import com.exceedo.vrpapi.domain.VrpJobStatus;
import com.exceedo.vrpapi.excepion.VrpJobException;
import com.exceedo.vrpapi.repository.ApiUserRepository;
import com.exceedo.vrpapi.repository.LinkRepository;
import com.exceedo.vrpapi.repository.LinkResourceUsageRepository;
import com.exceedo.vrpapi.repository.ResourceRepository;
import com.exceedo.vrpapi.repository.RouteTypeRepository;
import com.exceedo.vrpapi.repository.VisitRepository;

@RestController
public class VrpJobController {
	
	@Autowired
	private ApiUserRepository apiUserRepository;
	
	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private LinkResourceUsageRepository linkResourceUsageRepository;
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Autowired
	private RouteTypeRepository routeTypeRepository;
	
	@Autowired
	private VisitRepository visitRepository;
	
	@RequestMapping(path = "/job", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse createVrpJob(HttpServletRequest request, @RequestBody VrpJob job) throws VrpJobException {

		DataSaveResponse response = new DataSaveResponse();
		Principal principal = ((HttpServletRequest) request).getUserPrincipal();
		ApiUser client = apiUserRepository.findOne(principal.getName());
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
		apiUserRepository.save(client);
		response.setSaveCount(1);
		response.setStatus("success, total jobs created " + (jobCount + 1));
		return response;
	}
	
	@RequestMapping(path = "/jobs", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public List<VrpJob> getVrpJobs(HttpServletRequest request){
		
		Principal principal = ((HttpServletRequest) request).getUserPrincipal();
		ApiUser client = apiUserRepository.findOne(principal.getName());
		List<VrpJob> jobs =  client.getJobs();
		return jobs;
	}
	
	@RequestMapping(path = "/job/{jobId}/validate", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public VrpJobValidationResponse validateVrpJob(HttpServletRequest request, @PathVariable final String jobId){
		VrpJobValidationResponse response = new VrpJobValidationResponse();
		Principal principal = ((HttpServletRequest) request).getUserPrincipal();
		ApiUser client = apiUserRepository.findOne(principal.getName());
		List<VrpJob> jobs =  client.getJobs();
		VrpJob job = jobs.stream().filter(j -> j.getId().equals(jobId)).findFirst().orElse(null);
		response.setJobId(jobId);
		response.setJobName(job.getName());
		response.setStatus(VrpJobStatus.VALIDATED.name());
		LinkList linkList = linkRepository.findOne(jobId);
		if(CollectionUtils.isEmpty(linkList.getLinkList())) {
			response.setStatus(VrpJobStatus.INVALID.name());
			response.setLinkCount(0);
		}
		LinkResourceUsageList linkResourceUsageList =  linkResourceUsageRepository.findOne(jobId);
		if(CollectionUtils.isEmpty(linkResourceUsageList.getLinkResourceUsageList())) {
			response.setStatus(VrpJobStatus.INVALID.name());
			response.setLinkResourceUsageCount(0);
		}
		ResourceList resourceList = resourceRepository.findOne(jobId);
		if(CollectionUtils.isEmpty(resourceList.getResourceList())) {
			response.setStatus(VrpJobStatus.INVALID.name());
			response.setResourceCount(0);
		}		
		RouteTypeList routeTypeList = routeTypeRepository.findOne(jobId);
		if(CollectionUtils.isEmpty(routeTypeList.getRouteTypeList())) {
			response.setStatus(VrpJobStatus.INVALID.name());
			response.setRouteTypeCount(0);
		}
		VisitList visitList = visitRepository.findOne(jobId);
		if(CollectionUtils.isEmpty(visitList.getVisitList())) {
			response.setStatus(VrpJobStatus.INVALID.name());
			response.setVisitCount(0);
		}		
		return response;

	}

}

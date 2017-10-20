package com.exceedo.vrpapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exceedo.vrpapi.domain.VisitResourceUsage;
import com.exceedo.vrpapi.domain.VisitResourceUsageList;
import com.exceedo.vrpapi.repository.VisitResourceUsageRepository;


@RestController
public class VisitResourceUsageController {

	@Autowired
	private VisitResourceUsageRepository visitResourceUsageRepository;

	@RequestMapping(path = "job/{jobId}/visitResourceUsages", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveVisitResourceUsageList(@PathVariable String jobId, @RequestBody VisitResourceUsageList visitResourceUsageList) {

		DataSaveResponse response = new DataSaveResponse();
		visitResourceUsageList.setJobId(jobId);
		int recCount = visitResourceUsageList.getVisitResourceUsageList().size();
		visitResourceUsageRepository.save(visitResourceUsageList);
		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/visitResourceUsages", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public VisitResourceUsageList getVisitResourceUsageList(@PathVariable String jobId) {

		VisitResourceUsageList visitResourceUsageList = visitResourceUsageRepository.findOne(jobId);
		return visitResourceUsageList;
	}

	@RequestMapping(path = "job/{jobId}/visit/{visitId}/resource/{resourceId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public VisitResourceUsage getVisitResourceUsage(@PathVariable String jobId, @PathVariable String visitId, @PathVariable String resourceId) {
		VisitResourceUsageList visitResourceUsageList = visitResourceUsageRepository.findOne(jobId);
		for (VisitResourceUsage visitResourceUsage : visitResourceUsageList.getVisitResourceUsageList()) {
			if(visitResourceUsage.getVisitId() == Integer.valueOf(visitId) &&
					visitResourceUsage.getResourceId() == Integer.valueOf(resourceId)) {
				return visitResourceUsage;
			}
		}
		return null;
	}

	@RequestMapping(path = "job/{jobId}/visitResourceUsages", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteVisitResourceUsageList(@PathVariable String jobId) {

		DataDeleteResponse response = new DataDeleteResponse();
		VisitResourceUsageList visitResourceUsageList = visitResourceUsageRepository.findOne(jobId);
		response.setDeleteCount((int)visitResourceUsageList.getVisitResourceUsageList().size() );
		visitResourceUsageRepository.delete(jobId);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/visit/{visitId}/resource/{resourceId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteVisitResourceUsage(@PathVariable String jobId, @PathVariable String visitId, @PathVariable String resourceId) {

		DataDeleteResponse response = new DataDeleteResponse();
		VisitResourceUsageList visitResourceUsageList = visitResourceUsageRepository.findOne(jobId);
		VisitResourceUsage visitResourceUsageToBeDeleted = null;

		for (VisitResourceUsage visitResourceUsage : visitResourceUsageList.getVisitResourceUsageList()) {
			if(visitResourceUsage.getVisitId() == Integer.valueOf(visitId) &&
					visitResourceUsage.getResourceId() == Integer.valueOf(resourceId)) {
				visitResourceUsageToBeDeleted = visitResourceUsage;
			}
		}
		visitResourceUsageList.getVisitResourceUsageList().remove(visitResourceUsageToBeDeleted);
		visitResourceUsageRepository.save(visitResourceUsageList);
		response.setDeleteCount(1);
		response.setStatus("success");
		return response;
	}
	
	
	@RequestMapping(path = "job/{jobId}/visit/{visitId}/resource/{resourceId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveVisitResourceUsage(@PathVariable String jobId, @PathVariable String visitId, @PathVariable String resourceId, @RequestBody VisitResourceUsage visitResourceUsage) {

		DataSaveResponse response = new DataSaveResponse();
		VisitResourceUsageList visitResourceUsageList = visitResourceUsageRepository.findOne(jobId);
		for (VisitResourceUsage visitResourceUsageToBeUpdated : visitResourceUsageList.getVisitResourceUsageList()) {
			if(visitResourceUsageToBeUpdated.getVisitId() == Integer.valueOf(visitId) &&
					visitResourceUsageToBeUpdated.getResourceId() == Integer.valueOf(resourceId)) {
				visitResourceUsageToBeUpdated = visitResourceUsage;
			}
		}
		
		visitResourceUsageRepository.save(visitResourceUsageList);
		response.setSaveCount(1);
		response.setStatus("success");
		return response;
	}

}

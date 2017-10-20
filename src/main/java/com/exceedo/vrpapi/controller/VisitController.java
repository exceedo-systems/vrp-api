package com.exceedo.vrpapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exceedo.vrpapi.domain.Visit;
import com.exceedo.vrpapi.domain.VisitList;
import com.exceedo.vrpapi.repository.VisitRepository;


@RestController
public class VisitController {

	@Autowired
	private VisitRepository visitRepository;

	@RequestMapping(path = "job/{jobId}/visits", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveVisitList(@PathVariable String jobId, @RequestBody VisitList visitList) {

		DataSaveResponse response = new DataSaveResponse();
		visitList.setJobId(jobId);
		int recCount = visitList.getVisitList().size();
		visitRepository.save(visitList);
		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/visits", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public VisitList getVisitList(@PathVariable String jobId) {

		VisitList visitList = visitRepository.findOne(jobId);
		return visitList;
	}

	@RequestMapping(path = "job/{jobId}/visit/{visitId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Visit getVisit(@PathVariable String jobId, @PathVariable String visitId) {
		VisitList visitList = visitRepository.findOne(jobId);
		for (Visit visit : visitList.getVisitList()) {
			if(visit.getId() == Integer.valueOf(visitId)) {
				return visit;
			}
		}
		return null;
	}

	@RequestMapping(path = "job/{jobId}/visits", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteVisitList(@PathVariable String jobId) {

		DataDeleteResponse response = new DataDeleteResponse();
		VisitList visitList = visitRepository.findOne(jobId);
		response.setDeleteCount((int) visitList.getVisitList().size() );
		visitRepository.delete(jobId);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/visit/{visitId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteVisit(@PathVariable String jobId, @PathVariable String visitId) {

		DataDeleteResponse response = new DataDeleteResponse();
		VisitList visitList = visitRepository.findOne(jobId);
		Visit visitToBeDeleted = null;
		for (Visit visit : visitList.getVisitList()) {
			if(visit.getId() == Integer.valueOf(visitId)) {
				visitToBeDeleted = visit;
			}
		}
		visitList.getVisitList().remove(visitToBeDeleted);
		
		visitRepository.save(visitList);
		response.setDeleteCount(1);
		response.setStatus("success");
		return response;
	}
	
	
	@RequestMapping(path = "job/{jobId}/visit/{visitId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveVisit(@PathVariable String jobId, @PathVariable String visitId, @RequestBody Visit visit) {

		DataSaveResponse response = new DataSaveResponse();
		VisitList visitList = visitRepository.findOne(jobId);
		for (Visit visitToBeUpdated : visitList.getVisitList()) {
			if(visitToBeUpdated.getId() == Integer.valueOf(visitId)) {
				visitToBeUpdated = visit;
			}
		}
		
		visitRepository.save(visitList);
		response.setSaveCount(1);
		response.setStatus("success");
		return response;
	}

}

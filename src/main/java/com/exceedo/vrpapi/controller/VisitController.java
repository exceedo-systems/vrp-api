package com.exceedo.vrpapi.controller;

import java.util.List;

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

	@RequestMapping(path = "/visit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveVisitList(@RequestBody VisitList visitList) {

		DataSaveResponse response = new DataSaveResponse();
		int recCount = 0;
		for (Visit visit : visitList.getVisitList()) {
			visitRepository.save(visit);
			recCount++;
		}
		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/visit", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public VisitList getVisitList() {

		VisitList visitList = new VisitList();
		List<Visit> list = visitRepository.findAll();
		visitList.setVisitList(list);
		return visitList;
	}

	@RequestMapping(path = "/visit/{visitId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Visit getVisitList(@PathVariable String visitId) {
		Visit visit = visitRepository.findOne(new Integer(visitId));
		return visit;
	}

	@RequestMapping(path = "/visit", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteVisitList() {

		DataDeleteResponse response = new DataDeleteResponse();
		response.setDeleteCount((int) visitRepository.count());
		visitRepository.deleteAll();
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/visit/{visitId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteVisit(@PathVariable String visitId) {

		DataDeleteResponse response = new DataDeleteResponse();
		visitRepository.delete(new Integer(visitId));
		response.setDeleteCount(1);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/visit/{visitId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveVisit(@PathVariable String visitId, @RequestBody Visit visit) {

		DataSaveResponse response = new DataSaveResponse();
		int recCount = 1;

		Visit visitInDB = visitRepository.findOne(new Integer(visitId));
		visitInDB.setCost(visit.getCost());
		visitRepository.save(visitInDB);

		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

}

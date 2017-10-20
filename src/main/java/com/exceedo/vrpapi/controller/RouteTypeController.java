package com.exceedo.vrpapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exceedo.vrpapi.domain.RouteType;
import com.exceedo.vrpapi.domain.RouteTypeList;
import com.exceedo.vrpapi.repository.RouteTypeRepository;


@RestController
public class RouteTypeController {

	@Autowired
	private RouteTypeRepository routeTypeRepository;

	@RequestMapping(path = "job/{jobId}/routetypes", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveRouteTypeList(@PathVariable String jobId, @RequestBody RouteTypeList routeTypeList) {

		DataSaveResponse response = new DataSaveResponse();
		routeTypeList.setJobId(jobId);
		int recCount = routeTypeList.getRouteTypeList().size();
		routeTypeRepository.save(routeTypeList);
		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/routetypes", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public RouteTypeList getRouteTypeList(@PathVariable String jobId) {

		RouteTypeList routeTypeList = routeTypeRepository.findOne(jobId);
		return routeTypeList;
	}

	@RequestMapping(path = "job/{jobId}/routeType/{routeTypeId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public RouteType getRouteType(@PathVariable String jobId, @PathVariable String routeTypeId) {
		RouteTypeList routeTypeList = routeTypeRepository.findOne(jobId);
		for (RouteType routeType : routeTypeList.getRouteTypeList()) {
			if(routeType.getId() == Integer.valueOf(routeTypeId)) {
				return routeType;
			}
		}
		return null;
	}

	@RequestMapping(path = "job/{jobId}/routetypes", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteRouteTypeList(@PathVariable String jobId) {

		DataDeleteResponse response = new DataDeleteResponse();
		RouteTypeList routeTypeList = routeTypeRepository.findOne(jobId);
		response.setDeleteCount((int) routeTypeList.getRouteTypeList().size() );
		routeTypeRepository.delete(jobId);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/routeType/{routeTypeId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteRouteType(@PathVariable String jobId, @PathVariable String routeTypeId) {

		DataDeleteResponse response = new DataDeleteResponse();
		RouteTypeList routeTypeList = routeTypeRepository.findOne(jobId);
		RouteType routeTypeToBeDeleted = null;
		for (RouteType routeType : routeTypeList.getRouteTypeList()) {
			if(routeType.getId() == Integer.valueOf(routeTypeId)) {
				routeTypeToBeDeleted = routeType;
			}
		}
		routeTypeList.getRouteTypeList().remove(routeTypeToBeDeleted);
		
		routeTypeRepository.save(routeTypeList);
		response.setDeleteCount(1);
		response.setStatus("success");
		return response;
	}
	
	
	@RequestMapping(path = "job/{jobId}/routeType/{routeTypeId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveRouteType(@PathVariable String jobId, @PathVariable String routeTypeId, @RequestBody RouteType routeType) {

		DataSaveResponse response = new DataSaveResponse();
		RouteTypeList routeTypeList = routeTypeRepository.findOne(jobId);
		for (RouteType routeTypeToBeUpdated : routeTypeList.getRouteTypeList()) {
			if(routeTypeToBeUpdated.getId() == Integer.valueOf(routeTypeId)) {
				routeTypeToBeUpdated = routeType;
			}
		}
		
		routeTypeRepository.save(routeTypeList);
		response.setSaveCount(1);
		response.setStatus("success");
		return response;
	}

}

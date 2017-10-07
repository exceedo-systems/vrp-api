package com.exceedo.vrpapi.controller;

import java.util.List;

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

	@RequestMapping(path = "/routetype", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveRouteTypeList(@RequestBody RouteTypeList routeTypeList) {

		DataSaveResponse response = new DataSaveResponse();
		int recCount = 0;
		for (RouteType routeType : routeTypeList.getRouteTypeList()) {
			routeTypeRepository.save(routeType);
			recCount++;
		}
		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/routetype", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public RouteTypeList getRouteTypeList() {

		RouteTypeList routeTypeList = new RouteTypeList();
		List<RouteType> list = routeTypeRepository.findAll();
		routeTypeList.setRouteTypeList(list);
		return routeTypeList;
	}

	@RequestMapping(path = "/routetype/{routeTypeId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public RouteType getRouteTypeList(@PathVariable String routeTypeId) {
		RouteType routeType = routeTypeRepository.findOne(new Integer(routeTypeId));
		return routeType;
	}

	@RequestMapping(path = "/routetype", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteRouteTypeList() {

		DataDeleteResponse response = new DataDeleteResponse();
		response.setDeleteCount((int) routeTypeRepository.count());
		routeTypeRepository.deleteAll();
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/routetype/{routeTypeId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteRouteType(@PathVariable String routeTypeId) {

		DataDeleteResponse response = new DataDeleteResponse();
		routeTypeRepository.delete(new Integer(routeTypeId));
		response.setDeleteCount(1);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/routetype/{routeTypeId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveRouteType(@PathVariable String routeTypeId, @RequestBody RouteType routeType) {

		DataSaveResponse response = new DataSaveResponse();
		int recCount = 1;

		RouteType routeTypeInDB = routeTypeRepository.findOne(new Integer(routeTypeId));
		routeTypeInDB.setName(routeType.getName());
		routeTypeInDB.setMinValue(routeType.getMinValue());
		routeTypeInDB.setMaxValue(routeType.getMaxValue());
		routeTypeRepository.save(routeTypeInDB);

		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

}

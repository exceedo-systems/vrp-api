package com.exceedo.vrpapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exceedo.vrpapi.domain.Resource;
import com.exceedo.vrpapi.domain.ResourceList;
import com.exceedo.vrpapi.repository.ResourceRepository;

@RestController
public class ResourceController {

	@Autowired
	private ResourceRepository resourceRepository;

	@RequestMapping(path = "/resource", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveResorceList(@RequestBody ResourceList resourceList) {

		DataSaveResponse response = new DataSaveResponse();
		int recCount = 0;
		for (Resource resource : resourceList.getResourceList()) {
			resourceRepository.save(resource);
			recCount++;
		}
		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/resource", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResourceList getResourceList() {

		ResourceList resourceList = new ResourceList();
		List<Resource> list = resourceRepository.findAll();
		resourceList.setResourceList(list);
		return resourceList;
	}

	@RequestMapping(path = "/resource/{resourceId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Resource getResourceList(@PathVariable String resourceId) {
		Resource resource = resourceRepository.findOne(new Integer(resourceId));
		return resource;
	}

	@RequestMapping(path = "/resource", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteResourceList() {

		DataDeleteResponse response = new DataDeleteResponse();
		response.setDeleteCount((int) resourceRepository.count());
		resourceRepository.deleteAll();
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/resource/{resourceId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteResource(@PathVariable String resourceId) {

		DataDeleteResponse response = new DataDeleteResponse();
		resourceRepository.delete(new Integer(resourceId));
		response.setDeleteCount(1);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/resource/{resourceId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveResource(@PathVariable String resourceId, @RequestBody Resource resource) {

		DataSaveResponse response = new DataSaveResponse();
		int recCount = 1;

		Resource resourceInDB = resourceRepository.findOne(new Integer(resourceId));
		resourceInDB.setName(resource.getName());
		resourceInDB.setRouteId(resource.getRouteId());
		resourceRepository.save(resourceInDB);

		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

}

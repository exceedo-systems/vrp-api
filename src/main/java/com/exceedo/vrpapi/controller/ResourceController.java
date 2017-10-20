package com.exceedo.vrpapi.controller;

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

	@RequestMapping(path = "job/{jobId}/resources", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveResourceList(@PathVariable String jobId, @RequestBody ResourceList resourceList) {

		DataSaveResponse response = new DataSaveResponse();
		resourceList.setJobId(jobId);
		int recCount = resourceList.getResourceList().size();
		resourceRepository.save(resourceList);
		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/resources", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResourceList getResourceList(@PathVariable String jobId) {

		ResourceList resourceList = resourceRepository.findOne(jobId);
		return resourceList;
	}

	@RequestMapping(path = "job/{jobId}/resource/{resourceId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Resource getResource(@PathVariable String jobId, @PathVariable String resourceId) {
		ResourceList resourceList = resourceRepository.findOne(jobId);
		for (Resource resource : resourceList.getResourceList()) {
			if(resource.getId() == Integer.valueOf(resourceId)) {
				return resource;
			}
		}
		return null;
	}

	@RequestMapping(path = "job/{jobId}/resources", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteResourceList(@PathVariable String jobId) {

		DataDeleteResponse response = new DataDeleteResponse();
		ResourceList resourceList = resourceRepository.findOne(jobId);
		response.setDeleteCount((int) resourceList.getResourceList().size() );
		resourceRepository.delete(jobId);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/resource/{resourceId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteResource(@PathVariable String jobId, @PathVariable String resourceId) {

		DataDeleteResponse response = new DataDeleteResponse();
		ResourceList resourceList = resourceRepository.findOne(jobId);
		Resource resourceToBeDeleted = null;
		for (Resource resource : resourceList.getResourceList()) {
			if(resource.getId() == Integer.valueOf(resourceId)) {
				resourceToBeDeleted = resource;
			}
		}
		resourceList.getResourceList().remove(resourceToBeDeleted);
		
		resourceRepository.save(resourceList);
		response.setDeleteCount(1);
		response.setStatus("success");
		return response;
	}
	
	
	@RequestMapping(path = "job/{jobId}/resource/{resourceId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveResource(@PathVariable String jobId, @PathVariable String resourceId, @RequestBody Resource resource) {

		DataSaveResponse response = new DataSaveResponse();
		ResourceList resourceList = resourceRepository.findOne(jobId);
		for (Resource resourceToBeUpdated : resourceList.getResourceList()) {
			if(resourceToBeUpdated.getId() == Integer.valueOf(resourceId)) {
				resourceToBeUpdated = resource;
			}
		}
		
		resourceRepository.save(resourceList);
		response.setSaveCount(1);
		response.setStatus("success");
		return response;
	}

}

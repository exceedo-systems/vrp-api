package com.exceedo.vrpapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exceedo.vrpapi.domain.LinkResourceUsage;
import com.exceedo.vrpapi.domain.LinkResourceUsageList;
import com.exceedo.vrpapi.repository.LinkResourceUsageRepository;


@RestController
public class LinkResourceUsageController {

	@Autowired
	private LinkResourceUsageRepository linkResourceUsageRepository;

	@RequestMapping(path = "job/{jobId}/linkResourceUsages", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveLinkResourceUsageList(@PathVariable String jobId, @RequestBody LinkResourceUsageList linkResourceUsageList) {

		DataSaveResponse response = new DataSaveResponse();
		linkResourceUsageList.setJobId(jobId);
		int recCount = linkResourceUsageList.getLinkResourceUsageList().size();
		linkResourceUsageRepository.save(linkResourceUsageList);
		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/linkResourceUsages", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public LinkResourceUsageList getLinkResourceUsageList(@PathVariable String jobId) {

		LinkResourceUsageList linkResourceUsageList = linkResourceUsageRepository.findOne(jobId);
		return linkResourceUsageList;
	}

	@RequestMapping(path = "job/{jobId}/link/{linkId}/resource/{resourceId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public LinkResourceUsage getLinkResourceUsage(@PathVariable String jobId, @PathVariable String linkId, @PathVariable String resourceId) {
		LinkResourceUsageList linkResourceUsageList = linkResourceUsageRepository.findOne(jobId);
		for (LinkResourceUsage linkResourceUsage : linkResourceUsageList.getLinkResourceUsageList()) {
			if(linkResourceUsage.getLinkId() == Integer.valueOf(linkId) &&
					linkResourceUsage.getResourceId() == Integer.valueOf(resourceId)) {
				return linkResourceUsage;
			}
		}
		return null;
	}

	@RequestMapping(path = "job/{jobId}/linkResourceUsages", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteLinkResourceUsageList(@PathVariable String jobId) {

		DataDeleteResponse response = new DataDeleteResponse();
		LinkResourceUsageList linkResourceUsageList = linkResourceUsageRepository.findOne(jobId);
		response.setDeleteCount((int)linkResourceUsageList.getLinkResourceUsageList().size() );
		linkResourceUsageRepository.delete(jobId);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/link/{linkId}/resource/{resourceId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteLinkResourceUsage(@PathVariable String jobId, @PathVariable String linkId, @PathVariable String resourceId) {

		DataDeleteResponse response = new DataDeleteResponse();
		LinkResourceUsageList linkResourceUsageList = linkResourceUsageRepository.findOne(jobId);
		LinkResourceUsage linkResourceUsageToBeDeleted = null;

		for (LinkResourceUsage linkResourceUsage : linkResourceUsageList.getLinkResourceUsageList()) {
			if(linkResourceUsage.getLinkId() == Integer.valueOf(linkId) &&
					linkResourceUsage.getResourceId() == Integer.valueOf(resourceId)) {
				linkResourceUsageToBeDeleted = linkResourceUsage;
			}
		}
		linkResourceUsageList.getLinkResourceUsageList().remove(linkResourceUsageToBeDeleted);
		linkResourceUsageRepository.save(linkResourceUsageList);
		response.setDeleteCount(1);
		response.setStatus("success");
		return response;
	}
	
	
	@RequestMapping(path = "job/{jobId}/link/{linkId}/resource/{resourceId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveLinkResourceUsage(@PathVariable String jobId, @PathVariable String linkId, @PathVariable String resourceId, @RequestBody LinkResourceUsage linkResourceUsage) {

		DataSaveResponse response = new DataSaveResponse();
		LinkResourceUsageList linkResourceUsageList = linkResourceUsageRepository.findOne(jobId);
		for (LinkResourceUsage linkResourceUsageToBeUpdated : linkResourceUsageList.getLinkResourceUsageList()) {
			if(linkResourceUsageToBeUpdated.getLinkId() == Integer.valueOf(linkId) &&
					linkResourceUsageToBeUpdated.getResourceId() == Integer.valueOf(resourceId)) {
				linkResourceUsageToBeUpdated = linkResourceUsage;
			}
		}
		
		linkResourceUsageRepository.save(linkResourceUsageList);
		response.setSaveCount(1);
		response.setStatus("success");
		return response;
	}

}

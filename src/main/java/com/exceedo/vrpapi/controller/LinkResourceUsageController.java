package com.exceedo.vrpapi.controller;

import java.util.List;

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

	@RequestMapping(path = "/linkResourceUsage", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveLinkResourceUsageList(@RequestBody LinkResourceUsageList linkResourceUsageList) {

		DataSaveResponse response = new DataSaveResponse();
		int recCount = 0;
		for (LinkResourceUsage linkResourceUsage : linkResourceUsageList.getLinkResourceUsageList()) {
			linkResourceUsageRepository.save(linkResourceUsage);
			recCount++;
		}
		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/linkResourceUsage", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public LinkResourceUsageList getLinkResourceUsageList() {

		LinkResourceUsageList linkResourceUsageList = new LinkResourceUsageList();
		List<LinkResourceUsage> list = linkResourceUsageRepository.findAll();
		linkResourceUsageList.setLinkResourceUsageList(list);
		return linkResourceUsageList;
	}

	@RequestMapping(path = "/linkResourceUsage/{linkId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public LinkResourceUsage getLinkResourceUsage(@PathVariable String linkId) {
		LinkResourceUsage linkResourceUsage = linkResourceUsageRepository.findOne(new Integer(linkId));
		return linkResourceUsage;
	}

	@RequestMapping(path = "/linkResourceUsage", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteLinkResourceUsageList() {

		DataDeleteResponse response = new DataDeleteResponse();
		response.setDeleteCount((int) linkResourceUsageRepository.count());
		linkResourceUsageRepository.deleteAll();
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/linkResourceUsage/{linkId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteLinkResourceUsage(@PathVariable String linkId) {

		DataDeleteResponse response = new DataDeleteResponse();
		linkResourceUsageRepository.delete(new Integer(linkId));
		response.setDeleteCount(1);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/linkResourceUsage/{linkId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveLinkResourceUsage(@PathVariable String linkId, @RequestBody LinkResourceUsage linkResourceUsage) {

		DataSaveResponse response = new DataSaveResponse();
		int recCount = 1;

		LinkResourceUsage linkResourceUsageInDB = linkResourceUsageRepository.findOne(new Integer(linkId));
		linkResourceUsageInDB.setResourceId(linkResourceUsage.getResourceId());
		linkResourceUsageInDB.setResourceUsage(100.00);
		linkResourceUsageRepository.save(linkResourceUsageInDB);

		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

}

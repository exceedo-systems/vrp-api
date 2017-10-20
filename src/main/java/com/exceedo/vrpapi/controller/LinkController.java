package com.exceedo.vrpapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exceedo.vrpapi.domain.Link;
import com.exceedo.vrpapi.domain.LinkList;
import com.exceedo.vrpapi.repository.LinkRepository;


@RestController
public class LinkController {

	@Autowired
	private LinkRepository linkRepository;

	@RequestMapping(path = "job/{jobId}/links", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveLinkList(@PathVariable String jobId, @RequestBody LinkList linkList) {

		DataSaveResponse response = new DataSaveResponse();
		linkList.setJobId(jobId);
		int recCount = linkList.getLinkList().size();
		linkRepository.save(linkList);
		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/links", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public LinkList getLinkList(@PathVariable String jobId) {

		LinkList linkList = linkRepository.findOne(jobId);
		return linkList;
	}

	@RequestMapping(path = "job/{jobId}/link/{linkId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Link getLink(@PathVariable String jobId, @PathVariable String linkId) {
		LinkList linkList = linkRepository.findOne(jobId);
		for (Link link : linkList.getLinkList()) {
			if(link.getId() == Integer.valueOf(linkId)) {
				return link;
			}
		}
		return null;
	}

	@RequestMapping(path = "job/{jobId}/links", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteLinkList(@PathVariable String jobId) {

		DataDeleteResponse response = new DataDeleteResponse();
		LinkList linkList = linkRepository.findOne(jobId);
		response.setDeleteCount((int) linkList.getLinkList().size() );
		linkRepository.delete(jobId);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "job/{jobId}/link/{linkId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteLink(@PathVariable String jobId, @PathVariable String linkId) {

		DataDeleteResponse response = new DataDeleteResponse();
		LinkList linkList = linkRepository.findOne(jobId);
		Link linkToBeDeleted = null;
		for (Link link : linkList.getLinkList()) {
			if(link.getId() == Integer.valueOf(linkId)) {
				linkToBeDeleted = link;
			}
		}
		linkList.getLinkList().remove(linkToBeDeleted);
		
		linkRepository.save(linkList);
		response.setDeleteCount(1);
		response.setStatus("success");
		return response;
	}
	
	
	@RequestMapping(path = "job/{jobId}/link/{linkId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveLink(@PathVariable String jobId, @PathVariable String linkId, @RequestBody Link link) {

		DataSaveResponse response = new DataSaveResponse();
		LinkList linkList = linkRepository.findOne(jobId);
		for (Link linkToBeUpdated : linkList.getLinkList()) {
			if(linkToBeUpdated.getId() == Integer.valueOf(linkId)) {
				linkToBeUpdated = link;
			}
		}
		
		linkRepository.save(linkList);
		response.setSaveCount(1);
		response.setStatus("success");
		return response;
	}

}

package com.exceedo.vrpapi.controller;

import java.util.List;

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

	@RequestMapping(path = "/link", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveLinkList(@RequestBody LinkList linkList) {

		DataSaveResponse response = new DataSaveResponse();
		int recCount = 0;
		for (Link link : linkList.getLinkList()) {
			linkRepository.save(link);
			recCount++;
		}
		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/link", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public LinkList getLinkList() {

		LinkList linkList = new LinkList();
		List<Link> list = linkRepository.findAll();
		linkList.setLinkList(list);
		return linkList;
	}

	@RequestMapping(path = "/link/{linkId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Link getLinkList(@PathVariable String linkId) {
		Link link = linkRepository.findOne(new Integer(linkId));
		return link;
	}

	@RequestMapping(path = "/link", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteLinkList() {

		DataDeleteResponse response = new DataDeleteResponse();
		response.setDeleteCount((int) linkRepository.count());
		linkRepository.deleteAll();
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/link/{linkId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public DataDeleteResponse deleteLink(@PathVariable String linkId) {

		DataDeleteResponse response = new DataDeleteResponse();
		linkRepository.delete(new Integer(linkId));
		response.setDeleteCount(1);
		response.setStatus("success");
		return response;
	}

	@RequestMapping(path = "/link/{linkId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public DataSaveResponse saveLink(@PathVariable String linkId, @RequestBody Link link) {

		DataSaveResponse response = new DataSaveResponse();
		int recCount = 1;

		Link linkInDB = linkRepository.findOne(new Integer(linkId));
		linkInDB.setType(link.getType());
		linkRepository.save(linkInDB);

		response.setSaveCount(recCount);
		response.setStatus("success");
		return response;
	}

}

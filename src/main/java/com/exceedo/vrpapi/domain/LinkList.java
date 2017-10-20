package com.exceedo.vrpapi.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(value = { "jobId","linkList" })
@Document(collection="Link")
public class LinkList {
	
	@JsonProperty
	@Id
	private String jobId;
	
	@JsonProperty
	private List<Link> linkList;

}

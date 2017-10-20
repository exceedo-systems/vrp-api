package com.exceedo.vrpapi.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(value = { "jobId","resourceList" })
@Document(collection ="Resource")
public class ResourceList {
	
	@JsonProperty
	@Id
	private String jobId;
	
	@JsonProperty
	private List<Resource> resourceList;

}

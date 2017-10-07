package com.exceedo.vrpapi.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(value = { "resourceList" })
public class ResourceList {
	
	@JsonProperty
	private List<Resource> resourceList;

}

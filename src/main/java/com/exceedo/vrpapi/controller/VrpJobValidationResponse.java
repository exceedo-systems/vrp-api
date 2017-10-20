package com.exceedo.vrpapi.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VrpJobValidationResponse {
	
	@JsonProperty
	private String status;
	
	@JsonProperty
	private String jobName;
	
	@JsonProperty
	private String jobId;
	
	@JsonProperty
	private int linkCount;
	
	@JsonProperty
	private int resourceCount;
	
	@JsonProperty
	private int routeTypeCount;
	
	@JsonProperty
	private int visitCount;
	
	@JsonProperty
	private int linkResourceUsageCount;
	
	@JsonProperty
	private int visitResourceUsageCount;
	

}

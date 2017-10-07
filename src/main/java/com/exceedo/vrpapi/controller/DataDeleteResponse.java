package com.exceedo.vrpapi.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DataDeleteResponse {
	
	@JsonProperty
	private String status;
	
	@JsonProperty
	private int deleteCount;
	
	

}

package com.exceedo.vrpapi.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DataSaveResponse {
	
	@JsonProperty
	private String status;
	
	@JsonProperty
	private int saveCount;
	
	@JsonProperty
	private int skipCount;
	

}

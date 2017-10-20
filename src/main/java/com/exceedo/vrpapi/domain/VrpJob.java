package com.exceedo.vrpapi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;


@Data
@JsonPropertyOrder(value = {"id","name","status"})
@JsonInclude(Include.NON_NULL)
public class VrpJob {
	
	@JsonProperty
	private String id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private VrpJobStatus status;
	
}

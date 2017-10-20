package com.exceedo.vrpapi.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(value = { "jobId","routeTypeList" })
@Document(collection = "Route_Type")
public class RouteTypeList {
	
	@JsonProperty
	@Id
	private String jobId;
	
	@JsonProperty
	private List<RouteType> routeTypeList;

}

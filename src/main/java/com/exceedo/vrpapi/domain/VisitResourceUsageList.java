package com.exceedo.vrpapi.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(value = { "jobId","visitResourceUsageList" })
@Document(collection ="Visit_Resource_Usage")
public class VisitResourceUsageList {
	
	@JsonProperty
	private String jobId;
	
	@JsonProperty
	private List<VisitResourceUsage> visitResourceUsageList;

}

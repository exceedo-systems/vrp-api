package com.exceedo.vrpapi.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(value = { "visitList" })
public class VisitList {
	
	@JsonProperty
	private List<Visit> visitList;

}
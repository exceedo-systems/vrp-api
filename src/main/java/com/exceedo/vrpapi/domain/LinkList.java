package com.exceedo.vrpapi.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(value = { "linkList" })
public class LinkList {
	
	@JsonProperty
	private List<Link> linkList;

}

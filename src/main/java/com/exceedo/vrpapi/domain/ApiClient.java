package com.exceedo.vrpapi.domain;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonPropertyOrder(value = {"userName","password","apiKey","jobCount","jobs"})
@JsonInclude(Include.NON_NULL)
@Document(collection="API_Client")
public class ApiClient {
	
	@Id
	private String userName;
	
	private String password;
	
	private String apiKey;
	
	private int jobCount;
	
	private List<VrpJob> jobs;
	
	
}

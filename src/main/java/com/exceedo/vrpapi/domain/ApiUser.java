package com.exceedo.vrpapi.domain;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonPropertyOrder(value = {"userName","password","userType", "apiKey","jobCount","jobs"})
@JsonInclude(Include.NON_NULL)
@Document(collection="API_User")
public class ApiUser {
	
	@Id
	private String userName;
	
	private String password;
	
	private UserType userType;
	
	private String apiKey;
	
	private int jobCount;
	
	private List<VrpJob> jobs;
	
	
}

package com.exceedo.vrpapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

//@JsonPropertyOrder(value = { "id", "name", "routeId" })
@JsonPropertyOrder(value = { "id", "name", "routeId","amlStr","minLimit","maxLimit", "maxAt" })
@Data

public class Resource {
	
	@JsonProperty
	private int id;

	@JsonProperty
	private String name;
	
	@JsonProperty
	private int routeId;
	
	@JsonProperty
	private String  amlStr;
	
	@JsonProperty
	private double minLimit;
	
	@JsonProperty
	private double maxLimit;
	
	@JsonProperty
	private String maxAt;
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + routeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (routeId != other.routeId)
			return false;
		return true;
	}

}

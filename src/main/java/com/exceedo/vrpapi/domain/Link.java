package com.exceedo.vrpapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

//@JsonPropertyOrder(value = { "id", "type" })
@JsonPropertyOrder(value = { "id", "type","fromId","toId","cost" })
@Data

public class Link {

	@JsonProperty
	private int id;

	@JsonProperty
	private String type;
	
	@JsonProperty
	private int fromId;
	
	@JsonProperty
	private int toId;
	
	@JsonProperty
	private double cost;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Link other = (Link) obj;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}

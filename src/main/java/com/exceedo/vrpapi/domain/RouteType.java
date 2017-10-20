package com.exceedo.vrpapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(value = { "id", "name", "minValue", "maxValue" })
public class RouteType {

	@JsonProperty
	private int id;

	@JsonProperty
	private String name;

	@JsonProperty
	private int minValue;

	@JsonProperty
	private int maxValue;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + maxValue;
		result = prime * result + minValue;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		RouteType other = (RouteType) obj;
		if (id != other.id)
			return false;
		if (maxValue != other.maxValue)
			return false;
		if (minValue != other.minValue)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

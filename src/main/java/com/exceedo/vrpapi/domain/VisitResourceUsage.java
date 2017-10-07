package com.exceedo.vrpapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;


@JsonPropertyOrder(value = { "visitId", "resourceId", "resourceUsage", "minLimit","maxLimit" })
@Data
public class VisitResourceUsage {

	@JsonProperty
	private int visitId;

	@JsonProperty
	private int resourceId;

	@JsonProperty
	private double resourceUsage;

	@JsonProperty
	private double minLimit;

	@JsonProperty
	private double maxLimit;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(maxLimit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minLimit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + resourceId;
		temp = Double.doubleToLongBits(resourceUsage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + visitId;
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
		VisitResourceUsage other = (VisitResourceUsage) obj;
		if (Double.doubleToLongBits(maxLimit) != Double.doubleToLongBits(other.maxLimit))
			return false;
		if (Double.doubleToLongBits(minLimit) != Double.doubleToLongBits(other.minLimit))
			return false;
		if (resourceId != other.resourceId)
			return false;
		if (Double.doubleToLongBits(resourceUsage) != Double.doubleToLongBits(other.resourceUsage))
			return false;
		if (visitId != other.visitId)
			return false;
		return true;
	}
	
	
}

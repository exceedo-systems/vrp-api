package com.exceedo.vrpapi.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonPropertyOrder(value = { "linkId", "resourceId", "resourceUsage" })
@Data
@Document(collection="link_resource_usage")
public class LinkResourceUsage {

	@Id
	@JsonProperty
	private int linkId;

	@JsonProperty
	private int resourceId;

	@JsonProperty
	private double resourceUsage;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + linkId;
		result = prime * result + resourceId;
		long temp;
		temp = Double.doubleToLongBits(resourceUsage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		LinkResourceUsage other = (LinkResourceUsage) obj;
		if (linkId != other.linkId)
			return false;
		if (resourceId != other.resourceId)
			return false;
		if (Double.doubleToLongBits(resourceUsage) != Double.doubleToLongBits(other.resourceUsage))
			return false;
		return true;
	}

	
}

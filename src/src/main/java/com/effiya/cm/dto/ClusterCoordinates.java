package com.effiya.cm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClusterCoordinates {

	@JsonProperty("lat")
	private String lattitude;
	@JsonProperty("long")
	private String longitude;
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
	
}

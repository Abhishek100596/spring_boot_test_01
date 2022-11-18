package com.effiya.cm.dto;

public class ClusterDetails {
	
	private String cluster_id;
	private String owner_id;
	private String forest_id;
	private String country_name;
	private ClusterCoordinates cluster_coordinates;
	private String is_booked;
	private String is_active;
	private String miscellaneous;
	
	
	public String getMiscellaneous() {
		return miscellaneous;
	}
	public void setMiscellaneous(String miscellaneous) {
		this.miscellaneous = miscellaneous;
	}
	public String getCluster_id() {
		return cluster_id;
	}
	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public String getForest_id() {
		return forest_id;
	}
	public void setForest_id(String forest_id) {
		this.forest_id = forest_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public ClusterCoordinates getCluster_coordinates() {
		return cluster_coordinates;
	}
	public void setCluster_coordinates(ClusterCoordinates cluster_coordinates) {
		this.cluster_coordinates = cluster_coordinates;
	}
	public String getIs_booked() {
		return is_booked;
	}
	public void setIs_booked(String is_booked) {
		this.is_booked = is_booked;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	
	

}

package com.effiya.cm.dto;

import java.util.List;

public class ContractDetails {
	
	private String contract_id;
	private String owner_id;
	private String contract_country;
	private String payment_ref;
	private String contract_start_date;
	private String contract_expiry_date;
	private String number_of_clusters;
	private List<String> list_of_clusters;
	private String is_gifted;
	private String is_active;
	private String cluster_action;
	
	
	public String getCluster_action() {
		return cluster_action;
	}
	public void setCluster_action(String cluster_action) {
		this.cluster_action = cluster_action;
	}
	public String getContract_id() {
		return contract_id;
	}
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public String getContract_country() {
		return contract_country;
	}
	public void setContract_country(String contract_country) {
		this.contract_country = contract_country;
	}
	public String getPayment_ref() {
		return payment_ref;
	}
	public void setPayment_ref(String payment_ref) {
		this.payment_ref = payment_ref;
	}
	public String getContract_start_date() {
		return contract_start_date;
	}
	public void setContract_start_date(String contract_start_date) {
		this.contract_start_date = contract_start_date;
	}
	public String getContract_expiry_date() {
		return contract_expiry_date;
	}
	public void setContract_expiry_date(String contract_expiry_date) {
		this.contract_expiry_date = contract_expiry_date;
	}
	public String getNumber_of_clusters() {
		return number_of_clusters;
	}
	public void setNumber_of_clusters(String number_of_clusters) {
		this.number_of_clusters = number_of_clusters;
	}
	public List<String> getList_of_clusters() {
		return list_of_clusters;
	}
	public void setList_of_clusters(List<String> list_of_clusters) {
		this.list_of_clusters = list_of_clusters;
	}
	public String getIs_gifted() {
		return is_gifted;
	}
	public void setIs_gifted(String is_gifted) {
		this.is_gifted = is_gifted;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	
}

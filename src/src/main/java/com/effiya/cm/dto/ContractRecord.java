package com.effiya.cm.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ContractRecord {
	
	private String contract_country;
	private String contract_expiry_date;
	private String contract_id;
	private String contract_start_date;
	private String is_active;
	private String is_gifted;
	private List<String> list_of_clusters;
	private String number_of_clusters;
	private String payment_ref;
	private String record_type;
	@JsonIgnore
	private String user_id;
	private String deleted_reason;
	private String is_deleted;
	private String owner_id;
	
	
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public String getDeleted_reason() {
		return deleted_reason;
	}
	public void setDeleted_reason(String deleted_reason) {
		this.deleted_reason = deleted_reason;
	}
	public String getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getContract_country() {
		return contract_country;
	}
	public void setContract_country(String contract_country) {
		this.contract_country = contract_country;
	}
	public String getContract_expiry_date() {
		return contract_expiry_date;
	}
	public void setContract_expiry_date(String contract_expiry_date) {
		this.contract_expiry_date = contract_expiry_date;
	}
	public String getContract_id() {
		return contract_id;
	}
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	public String getContract_start_date() {
		return contract_start_date;
	}
	public void setContract_start_date(String contract_start_date) {
		this.contract_start_date = contract_start_date;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public String getIs_gifted() {
		return is_gifted;
	}
	public void setIs_gifted(String is_gifted) {
		this.is_gifted = is_gifted;
	}
	public List<String> getList_of_clusters() {
		return list_of_clusters;
	}
	public void setList_of_clusters(List<String> list_of_clusters) {
		this.list_of_clusters = list_of_clusters;
	}
	public String getNumber_of_clusters() {
		return number_of_clusters;
	}
	public void setNumber_of_clusters(String number_of_clusters) {
		this.number_of_clusters = number_of_clusters;
	}
	public String getPayment_ref() {
		return payment_ref;
	}
	public void setPayment_ref(String payment_ref) {
		this.payment_ref = payment_ref;
	}
	public String getRecord_type() {
		return record_type;
	}
	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	

}

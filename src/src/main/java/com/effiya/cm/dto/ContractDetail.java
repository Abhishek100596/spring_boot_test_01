package com.effiya.cm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContractDetail {
	@JsonProperty("Key")
	private String key;
	@JsonProperty("Record")
	private ContractRecord record;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public ContractRecord getRecord() {
		return record;
	}
	public void setRecord(ContractRecord record) {
		this.record = record;
	}
	
	
}

package com.effiya.cm.dto;

public class UserRegistrationRestTemplateResponseDto {

	private String success;
	private RestTemplateResponseDto message;
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public RestTemplateResponseDto getMessage() {
		return message;
	}
	public void setMessage(RestTemplateResponseDto message) {
		this.message = message;
	}
}

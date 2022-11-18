package com.effiya.cm.response;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

/**
 * @author Abhishek Kumar Singh
 */

public class RestResponse {
	
	private Object data;
	private boolean status;
	
	public RestResponse() {

	}

	public RestResponse(Object data2,boolean status) {
		this.data = data2;
		this.status  = status;
	}

	public static RestResponse ok(Object data){
		return new RestResponse(data,true);
	}
	
	public static RestResponse success(){
		return new RestResponse(null,true);
	}
	public static RestResponse fail(){
		return new RestResponse(null,false);
	}
	
	public static RestResponse fail(Object data){
		return new RestResponse(data,false);
	}
	
	public static ResponseEntity<RestResponse> httpOk(Object data){
		return new ResponseEntity<RestResponse>(ok(data), HttpStatus.OK);
	}
	public static ResponseEntity<RestResponse> httpOk(){
		return new ResponseEntity<RestResponse>(success(), HttpStatus.OK);
	}

	public static ResponseEntity<RestResponse> httpFail(Object data){
		return new ResponseEntity<RestResponse>(fail(data), HttpStatus.INTERNAL_SERVER_ERROR);
	}


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}



}

package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.dto.TimeDto;
import com.assignment.service.ApiService;

@RestController
public class ApiController {
	
	@Autowired
	ApiService apiService;

	//API to get current date and time of the server
	@RequestMapping(value="/timestamp", method = RequestMethod.GET)
	public TimeDto exchangeRate() {
		return apiService.getCurrentDateTime(); // returning server current date and time
	}
	
}

package com.assignment.service.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.assignment.dto.TimeDto;
import com.assignment.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService{

	@Override
	public TimeDto getCurrentDateTime() {
		Instant now= Instant.now(); //Calculate current timestamp of the server
		TimeDto timeDto = new TimeDto();
		timeDto.setTimestamp(now.toString());
		return timeDto;
	}
	
}

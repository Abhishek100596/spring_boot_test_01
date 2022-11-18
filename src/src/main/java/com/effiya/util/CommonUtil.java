package com.effiya.util;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	
	public int stringToInteger(String str) {
		if(null!=str) {
			int v = Integer.parseInt(str);
			return v;
		}
		else {
			return -1;
		}
	}
	
	public String integerToString(Integer i) {
		return String.valueOf(i);
	}
	
	public java.sql.Date stringToSqlDate(String str){
		
		java.sql.Date sqlDate = null;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(str!=null) {
				java.util.Date utilDate = dateFormatter.parse(str);
				sqlDate = new java.sql.Date(utilDate.getTime());
				return sqlDate;
				}
			else {
				return sqlDate;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return sqlDate;
		}
	}
	
	public java.sql.Timestamp stringToSqlTimestamp(String str){
		java.sql.Timestamp sqlDatetime = null;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(str!=null) {
				java.util.Date utilDatetime = dateFormatter.parse(str);
				sqlDatetime = new java.sql.Timestamp(utilDatetime.getTime());
				return sqlDatetime;
				}
			else {
				return sqlDatetime;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return sqlDatetime;
		}
	}
	
	public String convertSqlDateToStringDate(java.sql.Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// FOrmat in This Format or you change Change as well 
		String str= format.format(date);
		return str;
	}
	
	public String convertSqlTimestampToStringDate(java.sql.Timestamp date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// FOrmat in This Format or you change Change as well 
		String str= format.format(date);
		return str;
	}
}

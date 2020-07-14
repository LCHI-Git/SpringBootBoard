package com.web.springBoard.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.mysql.cj.xdevapi.JsonArray;
import com.web.springBoard.application.BoardService;



public class Utils {
	
	@Autowired
	static BoardService boardService;
	
	static Utils util;
	
	public static String getSbDate() {
		
		Date dt = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(HH:mm)");
			
		String sbDate = sdf.format(dt);
		
		return sbDate;
	}
	
	public static String getClientIP(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	    }

	    return ip;
	}
	
	
}

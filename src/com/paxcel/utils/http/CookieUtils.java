package com.paxcel.utils.http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	private static HttpServletRequest req;
	private static HttpServletResponse res;
	
	public static String getCookie(HttpServletRequest req , String cookie) {
		
		if(req.getCookies() == null) {
			return null;
		}
		
		for(Cookie c : req.getCookies()) {
			if(c.getName().equals(cookie)) {
				return c.getValue();
			}
		}
		
		return null;
	}
	public static boolean cookieExists(HttpServletRequest req , String cookie) {
	
		if(req.getCookies()==null) { // if no cookies found
			return false;
		}
		
		for(Cookie c : req.getCookies()) { // iterating over all the cookies 
			if(c.getName().equals(cookie)) {  // if cookie is found return 
				return true;
			}
		}
		
		return false; // no cookie found
	}
	
	public static void deleteCookie(HttpServletRequest req , String cookie)  {
		
		
		if(req.getCookies()==null) { // if no cookies found
			return;
		}
		
		for(Cookie c : req.getCookies()) {
			if(c.getName().equals(cookie)) {
				c.setMaxAge(-1);
			}
		}
	}
	
	public static void deleteRedundantCookies(HttpServletRequest req) {
		
		if(req.getCookies()==null) { // if no cookies found
			return;
		}
		
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>(); // map of encountered cookies
		
		for(Cookie c : req.getCookies()) {
			if(cookieMap.containsKey(c.getName())) { // if cookie already in the map
				c.setMaxAge(-1);  // delete the duplicate cookie
			}else { 
				cookieMap.put(c.getName(), c); // add cookie in the map
			}
		}
	}
	
	public static void createCookie(HttpServletResponse res , String name , String value , boolean httpOnly ) {
		Cookie cookie = new Cookie(name , value); // create a cookie object
		cookie.setHttpOnly(httpOnly); // set the http only status
		res.addCookie(cookie); // set cookie in response object
	}
}

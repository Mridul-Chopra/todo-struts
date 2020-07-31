package com.paxcel.utils.http;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class JsonUtils {
	
	public static <T> T fromJson(String json , Class c) {
		
		Gson gson = new Gson();
		T obj = (T)gson.fromJson(json, c);
		
		return obj;
	}
	
	public static String extractJson(HttpServletRequest req) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		
		try {
			BufferedReader reader = req.getReader();
			while((line = reader.readLine())!=null) {
				System.out.println(line);
				jb.append(line);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return jb.toString();
	}

}

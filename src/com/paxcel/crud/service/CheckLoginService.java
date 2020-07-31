package com.paxcel.crud.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.paxcel.crud.dao.CheckLoginDao;
import com.paxcel.utils.jwt.JwtUtils;

import io.jsonwebtoken.Claims;

public class CheckLoginService {

	private String token = null;
	private CheckLoginDao login = new CheckLoginDao();
	
	public String checkLogin(String username , String password){
		
		Map<String , Object> result =  login.checkLogin(username, password);
		
		if(result != null) {
			
			String id = UUID.randomUUID().toString();
			String issuer = "Mridul-Chopra-Paxcom";
			token = JwtUtils.generateToken(id, issuer, result, 0);
		}
		
		return token;
	}
}

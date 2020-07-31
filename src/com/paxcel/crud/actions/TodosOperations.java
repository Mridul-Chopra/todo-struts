package com.paxcel.crud.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.paxcel.crud.model.TodoModel;
import com.paxcel.crud.service.TodoOperationsService;
import com.paxcel.utils.http.CookieUtils;
import com.paxcel.utils.http.JsonUtils;
import com.paxcel.utils.jwt.JwtUtils;

public class TodosOperations implements ServletRequestAware , ServletResponseAware {
	
	private Object result = "";
	private int userId;
	private TodoOperationsService operations  =  new TodoOperationsService();

	/*
	 * Servlet Request and response 
	 */
	HttpServletRequest req;
	HttpServletResponse res;
	
	/*
	 * Some constants 
	 */
	private final String SUCCESS = "success";
	private final String FAILURE = "failure";
	private final String RE_LOGIN = "re_login";
	
	
	public String insert() {
		
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZW1haWwiOiJtcmlkdWxjaG9wcmE5N0BnbWFpbC5jb20iLCJ1c2VybmFtZSI6Ik1yaWR1bCBDaG9wcmEiLCJqdGkiOiI1NTRjOGVlNy1jM2RkLTRkYzUtOWM0OS0zZmU4ZDgzNjIwMWYiLCJpYXQiOjE1OTYxODc4MzksImlzcyI6Ik1yaWR1bC1DaG9wcmEtUGF4Y29tIn0.UtP5UoRpiY7mKg48lMjGXxjbJ2HCOO3FvX3qz-dCQHU";// CookieUtils.getCookie(req, "jwt");
		if(token == null) {
			return RE_LOGIN;
		}else {
			this.userId  = JwtUtils.extractIdfromToken(token);
			if(this.userId<0) {
				return RE_LOGIN;
			}
		}
		
		
		String data = JsonUtils.extractJson(req);
		TodoModel todo = JsonUtils.fromJson(data , TodoModel.class);
		todo.setUserId(userId);
		
		todo = operations.insertTodo(todo);
		if(todo == null) {
			result = "{error : true , message : 'Some error occoured'}";
		}
		result = todo;
		
		return SUCCESS;
	}
	
	public String delete() {
		
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZW1haWwiOiJtcmlkdWxjaG9wcmE5N0BnbWFpbC5jb20iLCJ1c2VybmFtZSI6Ik1yaWR1bCBDaG9wcmEiLCJqdGkiOiI1NTRjOGVlNy1jM2RkLTRkYzUtOWM0OS0zZmU4ZDgzNjIwMWYiLCJpYXQiOjE1OTYxODc4MzksImlzcyI6Ik1yaWR1bC1DaG9wcmEtUGF4Y29tIn0.UtP5UoRpiY7mKg48lMjGXxjbJ2HCOO3FvX3qz-dCQHU";// CookieUtils.getCookie(req, "jwt");
		if(token == null) {
			return RE_LOGIN;
		}else {
			this.userId  = JwtUtils.extractIdfromToken(token);
			if(this.userId<0) {
				return RE_LOGIN;
			}
		}
		
		String data = JsonUtils.extractJson(req);
		TodoModel todo = JsonUtils.fromJson(data , TodoModel.class);
		todo.setUserId(userId);
		
		this.result= operations.deleteTodo(todo);
		return SUCCESS;
		
	}
	
	public String update() {
		
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZW1haWwiOiJtcmlkdWxjaG9wcmE5N0BnbWFpbC5jb20iLCJ1c2VybmFtZSI6Ik1yaWR1bCBDaG9wcmEiLCJqdGkiOiI1NTRjOGVlNy1jM2RkLTRkYzUtOWM0OS0zZmU4ZDgzNjIwMWYiLCJpYXQiOjE1OTYxODc4MzksImlzcyI6Ik1yaWR1bC1DaG9wcmEtUGF4Y29tIn0.UtP5UoRpiY7mKg48lMjGXxjbJ2HCOO3FvX3qz-dCQHU";// CookieUtils.getCookie(req, "jwt");
		if(token == null) {
			return RE_LOGIN;
		}else {
			this.userId  = JwtUtils.extractIdfromToken(token);
			if(this.userId<0) {
				return RE_LOGIN;
			}
		}
		
		String data = JsonUtils.extractJson(req);
		TodoModel todo = JsonUtils.fromJson(data , TodoModel.class);
		todo.setUserId(userId);
		System.out.println(todo.toString());
		
		this.result = operations.changeStatus(todo);
		
		return SUCCESS;
	}

	
	/*
	 * Getters and Setters
	 */
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.req = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.res = response;
	}
	
}

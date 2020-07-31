package com.paxcel.crud.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.paxcel.crud.service.CheckLoginService;
import com.paxcel.utils.http.CookieUtils;

public class Login implements ServletRequestAware , ServletResponseAware {

	private String username = "";
	private String password = "";
	private String errorMessage = "This is error message";
	private CheckLoginService login = new CheckLoginService();
	
	/*
	 * Servlet request and response objects for cookie manipulation 
	 */
	protected HttpServletResponse servletResponse;
	protected HttpServletRequest servletRequest;
	
	/*
	 * Declaring some constants
	 */
	private final String SUCCESS = "success";
	private final String LOGIN_FAILED = "login_failed";
	private final String LOGIN = "login";
	
	public String execute() {
		
		/*
		 * Validations  
		 */
		if (username.equals("") | password.equals("") ) { 
			System.out.println("Nothing entered yet");
			return LOGIN;
		}
		
		String token  = login.checkLogin(username,password); // check the login credentials and get the token
		
		if(token != null){ // if token is received 
			CookieUtils.deleteCookie(servletRequest,"jwt"); // delete if already exists
			CookieUtils.createCookie(servletResponse,"jwt",token,true); // create a new cookie on login
			setErrorMessage("Login success");
			return SUCCESS;
		}else {
			setErrorMessage("Login failed. Invalid Credentials"); // set the error message to the user
			return LOGIN_FAILED;
		}
	}

	
	/*
	 * Getters and Setters 
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.servletResponse = response;
		
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
		
	}

}

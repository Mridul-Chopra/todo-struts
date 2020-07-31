package com.paxcel.crud.actions;

import com.paxcel.crud.service.SignUpService;

public class SignUp {

	private final String FAILURE = "failure";
	private final String SUCCESS = "success";
	
	private String email="";
	private String username="";
	private String password="";
	private String errorMessage = "";
	private String successMessage = "";
	
	private SignUpService signup = new SignUpService();
	
	public String execute() {
		
		if(email.equals("") | username.equals("") | password.equals("")) {
			this.setErrorMessage("Please fill all the fields.");
			return FAILURE;
		}
		
		String status = signup.signUp(email, password, username);
		if(signup.getStatus()==1) {
			this.setSuccessMessage(status);
			return SUCCESS;
		}else {
			this.setErrorMessage(status);
			return FAILURE;
		}
		
		
	}
	

	/*
	 * Getters and Setters 
	 */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getSuccessMessage() {
		return successMessage;
	}


	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}






	
}

package com.paxcel.crud.service;

import com.paxcel.crud.dao.SignUpDao;

public class SignUpService {
	
	private SignUpDao signUp = new SignUpDao(); // dao layer object
	private int status = -100;
	
	/*
	 * Getters and Setters 
	 */
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

	public String signUp(String email , String password , String username) {
		
		 status = signUp.signUp(email , password , username); // get status of sign up from the dao layer 
		
		/*
		 * Return the string status based upon the status codes
		 * 	 1	:	successful sign up
		 *	-1	: 	error occurred
		 *	 0	: 	duplicate entry  
		 */
		switch(status) {
			case 1 : {
				return "Sign up successful. Please login to continue ";
			}
			case -1 : {
				return "Some error occoured. Please try again later";
			}
			case 0 :{
				return "Email already registered. Please select a new one";
			}
			default : {
				return null;
			}
		}
	}
}

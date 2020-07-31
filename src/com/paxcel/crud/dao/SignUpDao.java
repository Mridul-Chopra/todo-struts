package com.paxcel.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.paxcel.crud.pool.Pool;

public class SignUpDao {

	public int signUp(String email , String password , String username) {
		
		try {
			Connection cn = Pool.getConnection(); // get the connection from the pool
			 
			/*
			 *	Check if user with email already exists
			 */
			String sql ="Select * from users where email = ?"; // sql statement to select user with given username
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1, email);
			
			ResultSet rs = st.executeQuery();

			if(rs.next()) { // if user is found 
				return 0; // return that user already exists
			}
			
			/*
			 * if username is unique  insert into the database
			 */
			sql = "Insert into users (email,password,username) values (? , ? , ?)"; 
			st = cn.prepareStatement(sql);
			st.setString(1,email);
			st.setString(2, password);
			st.setString(3, username);
			
			st.executeUpdate();
			return 1;// successful status
			
		}catch(Exception e) {
			System.out.println(e);
			return -1;  // error status
		}
		
	}
}

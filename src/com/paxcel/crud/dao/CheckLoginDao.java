package com.paxcel.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.paxcel.crud.pool.Pool;

public class CheckLoginDao {

	
	public Map<String , Object> checkLogin(String username , String password){
		
		try{
			
			Connection cn = Pool.getConnection();
			String sql = "Select * from users where email = ? and password = ?"; // sql query to be executed
			PreparedStatement st = cn.prepareStatement(sql); // prepared statement 
			
			// set the parameters in the statement
			st.setString(1, username);
			st.setString(2, password);
			
			ResultSet rs = st.executeQuery(); // execute the query and get the result
			
			if(rs.next()){		// if such username and password exists
				
				Map<String,Object> result = new HashMap<String,Object>(); // hashmap of the result values
				
				/*Put the values in the hashmap*/
				result.put("username" , rs.getString("username"));
				result.put("email", rs.getString("email"));
				result.put("id", rs.getInt("id"));
	
				return result; // return the result
			}else {
				return null;
			}
			
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
}

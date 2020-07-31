package com.paxcel.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paxcel.crud.model.TodoModel;
import com.paxcel.crud.pool.Pool;

public class GetTodosDao {
	
	public List<TodoModel> getTodos(int id){
		
		try {
			
			Connection cn = Pool.getConnection();
			
			String sql = "Select * from todos where user_id = ?";
			PreparedStatement st = cn.prepareStatement(sql);
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			List<TodoModel> result = new ArrayList<TodoModel>();
			
			while(rs.next()) {
				
				String todo = rs.getString("todo");
				Boolean isDone = rs.getBoolean("isDone");
			
				int todoId = rs.getInt("todo_id");
				int userId = rs.getInt("user_id");
						
				TodoModel todoInfo = new TodoModel(todoId , userId , todo , isDone);
				
				result.add(todoInfo);
			}
			return result;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}
}

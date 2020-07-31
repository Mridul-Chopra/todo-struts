package com.paxcel.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.paxcel.crud.model.TodoModel;
import com.paxcel.crud.pool.Pool;

public class TodoOperationsDao {

	private Connection cn = Pool.getConnection();
	
	public int insertTodo(TodoModel todoModel) {
		try {
			
			int user = todoModel.getUserId();
			String todo = todoModel.getTodo();
			
			/*
			 * Query to be executed
			 */
			String sql = "Insert into todos(user_id,todo,isDone) values (?,?,?)";  
			PreparedStatement st = cn.prepareStatement(sql);
			
			/*
			 *  set the values in the statement 
			 */
			st.setInt(1, user);
			st.setString(2,todo);
			st.setBoolean(3, false);
			
			// execute the query
			st.executeUpdate();
			
			sql = "SELECT MAX(todo_id) AS todo_id FROM todos;";
			st = cn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return rs.getInt("todo_id");
			}else {
				return 0;
			}
			
			
		}catch(Exception e) {
			System.out.println(e);
			return -1;
		}

	}
	
	public boolean deleteTodo(TodoModel todoModel) {
		try {
			
			int user = todoModel.getTodoId();
			int todoId = todoModel.getTodoId();
			/*
			 * Query to be executed 
			 */
			String sql = "Delete from todos where user_id= ? and todo_id = ?";
			PreparedStatement st = cn.prepareStatement(sql);
			
			// prepare the statement 
			st.setInt(1, user);
			st.setInt(2, todoId);
			
			st.executeUpdate(); // execute the query
	
			return true; // return true on success
 		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	
	public boolean changeStatus(TodoModel todoModel) {
		
		try {
			
			boolean isDone = todoModel.isDone();
			int userId = todoModel.getUserId();
			int todoId = todoModel.getTodoId();
			
			/*
			 * Query to be executed 
			 */
			String sql = "Update todos Set isDone = ? where user_id = ? and todo_id = ? ";
			PreparedStatement st = cn.prepareStatement(sql);
			
			// prepare the statement
			st.setBoolean(1, isDone);
			st.setInt(2, userId);
			st.setInt(3, todoId);
			
			st.executeUpdate(); // execute the statement 
			
			return true; // return true on success
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}

	}
}

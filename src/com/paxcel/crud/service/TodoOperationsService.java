package com.paxcel.crud.service;

import com.paxcel.crud.dao.TodoOperationsDao;
import com.paxcel.crud.model.TodoModel;
import com.paxcel.utils.jwt.JwtUtils;

import io.jsonwebtoken.Claims;

public class TodoOperationsService {

	private TodoOperationsDao operations = new TodoOperationsDao();

	public TodoModel insertTodo(TodoModel todoModel) {
		
		int todoId = operations.insertTodo(todoModel);
		todoModel.setTodoId(todoId);
		
		return todoModel;
	}

	public boolean deleteTodo( TodoModel todoModel) {
		
		boolean status = operations.deleteTodo(todoModel);
		return status;
	}
	
	public boolean changeStatus( TodoModel todoModel) {
		
		boolean status = operations.changeStatus(todoModel);
		return status;
	}
	
	
}

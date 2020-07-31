package com.paxcel.crud.service;

import java.util.List;
import java.util.Map;

import com.paxcel.crud.dao.GetTodosDao;
import com.paxcel.crud.model.TodoModel;
import com.paxcel.utils.jwt.JwtUtils;

import io.jsonwebtoken.Claims;

public class GetTodosService {

	private GetTodosDao todos = new GetTodosDao();
	
	public List<TodoModel> getTodos(int id){
	
		return  this.todos.getTodos(id);
		
	}
}
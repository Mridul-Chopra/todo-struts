package com.paxcel.crud.actions;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.paxcel.crud.model.TodoModel;
import com.paxcel.crud.service.GetTodosService;
import com.paxcel.utils.http.CookieUtils;
import com.paxcel.utils.jwt.JwtUtils;

public class GetTodos implements ServletRequestAware, ServletResponseAware{
	
	private List<TodoModel> todosList ; // contains todos to be displayed to the user
	private GetTodosService todos = new GetTodosService();
	private String message ="";
	private int userId;
	
	/*
	 * Servlet request and response  
	 */
	
	HttpServletRequest req ;
	HttpServletResponse res;
		
	/*Some constants declared*/
	private final String SUCCESS = "success"; 
	private final String FAILURE = "failure";
	private final String RE_LOGIN = "re_login";
	
	public String execute() {
	
		String token = CookieUtils.getCookie(req, "jwt");
		if(token == null) {
			return RE_LOGIN;
		}else {
			this.userId  = JwtUtils.extractIdfromToken(token);
			if(this.userId<0) {
				return RE_LOGIN;
			}
		}
		
		List<TodoModel> todos = this.todos.getTodos(userId);
		if(todos == null) {
			return RE_LOGIN;
		}
		if(todos.isEmpty()) {
			this.setMessage("No todos found. Please try adding some...");
		}
		this.setTodosList(todos);
		return SUCCESS;
	}
		
	/*
	 *
	 *  Getter and Setters
	 * 
	 */
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.res = response;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.req = request;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<TodoModel> getTodosList() {
		return todosList;
	}

	public void setTodosList(List<TodoModel> todosList) {
		this.todosList = todosList;
	}


	
}

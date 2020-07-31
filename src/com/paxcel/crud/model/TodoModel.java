package com.paxcel.crud.model;

public class TodoModel {

	private int todoId;
	private int userId;
	private String todo;
	private boolean done;
	
	public TodoModel(int todoId, int userId, String todo, boolean done) {
		super();
		this.todoId = todoId;
		this.userId = userId;
		this.todo = todo;
		this.done = done;
	}
	
	
	/*
	 * Getters and Setters 
	 */
	public int getTodoId() {
		return todoId;
	}
	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}

	public boolean isDone() {
		return done;
	}


	public void setDone(boolean done) {
		this.done = done;
	}


	@Override
	public String toString() {
		return "TodoModel [todoId=" + todoId + ", userId=" + userId + ", todo=" + todo + ", done=" + done + "]";
	}

	
	
}

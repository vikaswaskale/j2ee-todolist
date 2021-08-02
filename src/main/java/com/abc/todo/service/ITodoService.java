package com.abc.todo.service;

import java.util.List;
import java.util.Optional;

import com.abc.todo.model.Todo;

public interface ITodoService {

	List<Todo> getTodosByUser(String user);

	Optional<Todo> getTodoById(long id);

	void updateTodo(Todo todo);

	void deleteTodo(long id);
	
	void saveTodo(Todo todo);

}
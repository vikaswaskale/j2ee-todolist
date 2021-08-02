package com.abc.todo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.abc.todo.model.Todo;
import com.abc.todo.repository.TodoRepository;
import com.abc.todo.service.TodoService;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

	@Mock
	TodoRepository todoRepository;;

	@InjectMocks
	TodoService todoService;

	private List<Todo> todoList;

	private Todo todo1;

	private Todo todo2;
	
	@Before
	public void setup() {
		todoList = new ArrayList<Todo>();
		 todo1 = new Todo(1L,"john", "first task", false, LocalDateTime.now(), LocalDateTime.now());
		 todo2 = new Todo(2L,"john", "Third task", false, LocalDateTime.now(), LocalDateTime.now());
		
		
	}
	
	@Test
	public void testGetTodosByUser() {
		 todoList.add(todo1);
		 todoList.add(todo2);
		 
		when(todoRepository.findByUserName("john")).thenReturn(todoList);
		List<Todo> todosByUser = todoService.getTodosByUser("john");
		assertNotNull(todosByUser);
		assertEquals(2,todosByUser.size());
		assertEquals("first task",todosByUser.get(0).getDescription());
	}

	@Test
	public void testGetTodosById() {
		 Optional<Todo> todoOptional = Optional.of(todo1);
		 
		when(todoRepository.findById(1L)).thenReturn(todoOptional);
		 Optional<Todo> todoById = todoService.getTodoById(1L);
		assertNotNull(todoById);
		assertEquals("first task",todoById.get().getDescription());
	}
	
	@Test
	public void testSaveTodo() {
		Todo saveTodo = new Todo(3L,"john", "Third task", false, LocalDateTime.now(), LocalDateTime.now());
		when(todoRepository.save(saveTodo)).thenReturn(saveTodo);
		todoService.saveTodo(saveTodo);
		 assertNotNull(saveTodo);
		 assertEquals("Third task",saveTodo.getDescription());
	}

	@Test
	public void testUpdateTodo() {
		Todo updateTodo = new Todo(4L,"john", "Third task", false, LocalDateTime.now(), LocalDateTime.now());
		when(todoRepository.save(updateTodo)).thenReturn(updateTodo);
		updateTodo.setCompleted(true);
		todoService.saveTodo(updateTodo);
		verify(todoRepository).save(updateTodo);
		 assertNotNull(updateTodo);
		 assertEquals(true,updateTodo.isCompleted());
	}

	@Test
	public void testDeleteTodo() {
		todoRepository.deleteById(5L);
		todoService.deleteTodo(5L);
		verify(todoRepository).deleteById(5L);
		
	}


}

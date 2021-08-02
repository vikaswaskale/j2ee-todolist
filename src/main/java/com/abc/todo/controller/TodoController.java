package com.abc.todo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.todo.model.Todo;
import com.abc.todo.service.ITodoService;

/**
 * Todo Controller to handle the requests
 * 
 * @author vikas
 *
 */
@Controller
public class TodoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	private ITodoService todoService;

	

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		LOGGER.info("Entry Todo list");
		
		String name = getLoggedInUserName(model);
		model.put("todos", todoService.getTodosByUser(name));
		
		LOGGER.info("Exit Todo list");
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		LOGGER.info("Entry add todo");
		
		model.addAttribute("todo", new Todo());
		
		LOGGER.info("Exit add todo");
		return "todo";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam long id) {
		LOGGER.info("Entry delete todo "+id);
		
		todoService.deleteTodo(id);
		
		LOGGER.info("Exit delete todo "+id);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
		
		Todo todo = todoService.getTodoById(id).get();
		model.put("todo", todo);
		
		LOGGER.info("Exit update todo ");
		return "update";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		LOGGER.info("Entry update todo "+todo.getId());
		
		if (result.hasErrors()) {
			LOGGER.error("Error update todo : "+result.getAllErrors());
			return "todo";
		}
		todo.setUserName(getLoggedInUserName(model));
		todoService.updateTodo(todo);
		
		LOGGER.info("Exit update todo ");
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		LOGGER.info("Entry add todo "+todo.getId());
		
		if (result.hasErrors()) {
			LOGGER.error("Error add todo : "+result.getAllErrors());
			return "todo";
		}

		todo.setUserName(getLoggedInUserName(model));
		todoService.saveTodo(todo);
		
		LOGGER.info("Exit add todo "+todo.getId());
		return "redirect:/list-todos";
	}
}

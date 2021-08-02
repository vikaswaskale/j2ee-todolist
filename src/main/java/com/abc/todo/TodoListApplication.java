package com.abc.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Application for todo list
 * 
 * @author vikas
 *
 */
@SpringBootApplication
public class TodoListApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}
}

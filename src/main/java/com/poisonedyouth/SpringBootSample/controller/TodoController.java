package com.poisonedyouth.SpringBootSample.controller;

import com.poisonedyouth.SpringBootSample.model.Todo;
import com.poisonedyouth.SpringBootSample.service.TodoService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TodoController {

	private TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping(value = "/list-todos")
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName();
		model.put("name", name);
		model.put("todos", todoService.retrieveTodos(name));
		return "list-todos";
	}

	@GetMapping(value = "/add-todo")
	public String showTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo());
		return "todo";
	}

	@PostMapping(value = "/add-todo")
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String name = getLoggedInUserName();
		model.put("name", name);
		todoService.addTodo(name, todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";
	}

	@GetMapping(value = "/delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}

	@GetMapping(value = "/update-todo")
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}

	@PostMapping(value = "/update-todo")
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String name = getLoggedInUserName();
		model.put("name", name);
		todo.setUser(name);
		todoService.updateTodo(todo);
		return "redirect:/list-todos";
	}

	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
}

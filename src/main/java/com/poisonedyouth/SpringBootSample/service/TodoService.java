package com.poisonedyouth.SpringBootSample.service;

import com.poisonedyouth.SpringBootSample.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int todoCount = 3;

	static {
		todos.add(new Todo(1, "in28Minutes", "Learns Spring MVC", new Date(), false));
		todos.add(new Todo(2, "matthias", "Learns Struts", new Date(), false));
		todos.add(new Todo(3, "in28Minutes", "Learns Hibernate", new Date(), false));
	}

	public List<Todo> retrieveTodos(String user) {
		return todos.stream().filter(todo -> todo.getUser().equalsIgnoreCase(user)).collect(Collectors.toList());
	}

	public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
		todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
	}

	public void deleteTodo(int id) {
		Todo todo = todos.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
		if (todo != null) {
			todos.remove(todo);
		}
	}

	public Todo retrieveTodo(int id) {
		return todos.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
	}

	public void updateTodo(Todo todo) {
		deleteTodo(todo.getId());
		todos.add(todo);
	}
}

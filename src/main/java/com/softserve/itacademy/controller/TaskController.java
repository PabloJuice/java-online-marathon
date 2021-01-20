package com.softserve.itacademy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	TaskService taskService;

	@Autowired
	ToDoService toDoService;

	@Autowired
	UserService userService;

	@GetMapping("all/todos/{todo_id}")
	public String getAllTasksByTodoId(@PathVariable(name="todo_id")long todoId,
			Model model) {
		model.addAttribute("tasks", taskService.getByTodoId(todoId));
		model.addAttribute("title", "All Tasks From " + toDoService.readById(todoId).getTitle());
		List<User> possible_collaborators = new ArrayList<>(userService.getAll());
		possible_collaborators.removeAll(toDoService.readById(todoId).getCollaborators());
		possible_collaborators.remove(toDoService.readById(todoId).getOwner());
		model.addAttribute("possible_collaborators", possible_collaborators);
		model.addAttribute("already_collaborators", toDoService.readById(todoId).getCollaborators());
		return "todo-tasks";
	}
//    @GetMapping("/create/todos/{todo_id}")
//    public String create(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @PostMapping("/create/todos/{todo_id}")
//    public String create(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{task_id}/update/todos/{todo_id}")
//    public String update(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @PostMapping("/{task_id}/update/todos/{todo_id}")
//    public String update(//add needed parameters) {
//       //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{task_id}/delete/todos/{todo_id}")
//    public String delete(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
}

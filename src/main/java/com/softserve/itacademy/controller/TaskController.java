package com.softserve.itacademy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.StateService;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

	@Autowired
	StateService stateService;

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
		model.addAttribute("todo_id", todoId);

		return "todo-tasks";
	}

	@GetMapping("/{todo_id}/delete/task/{task_id}")
	public String delete(@PathVariable(name = "todo_id")long todoId, @PathVariable(name = "task_id") long taskId) {
		taskService.delete(taskId);
		return "redirect:/tasks/all/todos/"+todoId;
	}

	@GetMapping("/{todo_id}/update/task/{task_id}")
	public String update(@PathVariable(name = "todo_id")long todoId, @PathVariable(name = "task_id") long taskId,
						 Model model) {
		model.addAttribute("task",taskService.readById(taskId));
		model.addAttribute("todo_id", todoId);
		model.addAttribute("states", stateService.getAll());
		return "update-task";
	}

	@PostMapping("/{todo_id}/update/task/{task_id}")
	public String update(@PathVariable(name="todo_id")long todoId, @ModelAttribute(name = "task") Task task) {
		task.setTodo(toDoService.readById(todoId));
		taskService.update(task);
		return "redirect:/tasks/all/todos/"+todoId;
	}

	@GetMapping("/{todo_id}/create/task")
	public String create(Model model, @PathVariable(name = "todo_id")long todoId) {
		model.addAttribute("task", new Task());
		model.addAttribute("states", stateService.getAll());
		model.addAttribute("todo_id", todoId);
		return "create-task";
	}

	@PostMapping("/{todo_id}/create/taskCreate")
	public String create(@ModelAttribute(name = "task") Task task,
						 @PathVariable(name = "todo_id")long todoId) {
		task.setTodo(toDoService.readById(todoId));
		taskService.create(task);
		return "redirect:/tasks/all/todos/"+todoId;
	}
//	@GetMapping("/all/users/{owner_id}/delete/{todo_id}")
//	public String delete(@PathVariable(name = "todo_id")long todo_id, @PathVariable(name = "owner_id") long owner_id) {
//		toDoService.delete(todo_id);
//		return "redirect:all/todos/" + todo_id;
//	}

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

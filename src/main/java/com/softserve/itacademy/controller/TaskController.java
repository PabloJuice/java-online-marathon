package com.softserve.itacademy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
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
	@GetMapping("all/todos/{todo_id}/deleteCollaborator/{collaborator_id}")
	public String deleteCollaborator(@PathVariable(name = "todo_id")long todo_id, @PathVariable(name = "collaborator_id") long collaborator_id) {
		List<User> collaborators = new ArrayList<>(toDoService.readById(todo_id).getCollaborators().stream().filter(user -> user.getId() != collaborator_id).collect(Collectors.toList()));
		ToDo toDo = toDoService.readById(todo_id);
		toDo.setCollaborators(collaborators);
		toDoService.update(toDo);
		return "redirect:/tasks/all/todos/" + todo_id;
	}

	@GetMapping("all/todos/{todo_id}/addCollaborator/{collaborator_id}")
	public String addCollaborator(@PathVariable(name = "todo_id")long todo_id, @PathVariable(name = "collaborator_id") long collaborator_id) {
		List<User> collaborators = new ArrayList<>(toDoService.readById(todo_id).getCollaborators());
		collaborators.add(userService.readById(collaborator_id));
		ToDo toDo = toDoService.readById(todo_id);
		toDo.setCollaborators(collaborators);
		toDoService.update(toDo);
		return "redirect:/tasks/all/todos/" + todo_id;
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

package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.softserve.itacademy.service.ToDoService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/todos")
public class ToDoController {
	@Autowired
    ToDoService toDoService;

    @Autowired
    UserService userService;
	
	@GetMapping("/all/users/{user_id}")
	public String getAll(@PathVariable(name = "user_id") long userId, 
			Model model) {
      model.addAttribute("todos",toDoService.getByUserId(userId));
      model.addAttribute("title", "All ToDo Lists of " + userService.readById(userId).getFirstName() + " " + userService.readById(userId).getLastName());
      return "todos-user";
  }

    @GetMapping("/all/users/{owner_id}/create")
    public String create(Model model, @PathVariable(name = "owner_id") long owner_id) {
	    ToDo toDo = new ToDo();
	    toDo.setOwner(userService.readById(owner_id));
	    toDo.setCreatedAt(LocalDateTime.now());
        model.addAttribute("todo", toDo);
        return "create-todo";
    }

    @PostMapping("/all/users/{owner_id}/createToDo")
    public String create(@ModelAttribute(name = "todo") ToDo toDo, @PathVariable(name = "owner_id") long owner_id) {
        toDoService.create(toDo);
        return "redirect:/todos/all/users/" + owner_id;
    }
//
//    @GetMapping("/{id}/tasks")
//    public String read(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
    @GetMapping("/all/users/{owner_id}/update/{todo_id}")
    public String update(@PathVariable(name = "todo_id")long todo_id, @PathVariable(name = "owner_id") long owner_id, Model model) {
	    model.addAttribute("todo", toDoService.getByUserId(owner_id).stream().filter(toDo -> toDo.getId() == todo_id).findFirst().get());
        return "update-todo";
    }

    @PostMapping("/all/users/{owner_id}/update/")
    public String update(@ModelAttribute(name = "todo") ToDo toDo, @PathVariable long owner_id) {
        toDoService.update(toDo);
        return "redirect:/todos/all/users/" + owner_id;
    }
//
    @GetMapping("/all/users/{owner_id}/delete/{todo_id}")
    public String delete(@PathVariable(name = "todo_id")long todo_id, @PathVariable(name = "owner_id") long owner_id) {
        toDoService.delete(todo_id);
		return "redirect:/todos/all/users/" + owner_id;
    }
//
//    
//
//    @GetMapping("/{id}/add")
//    public String addCollaborator(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{id}/remove")
//    public String removeCollaborator(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
}

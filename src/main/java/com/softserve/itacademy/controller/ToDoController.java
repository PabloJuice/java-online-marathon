package com.softserve.itacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.itacademy.service.ToDoService;

@Controller
@RequestMapping("/todos")
public class ToDoController {
	@Autowired
    ToDoService toDoService;
	
	@GetMapping("/all/users/{user_id}")
	public String getAll(@PathVariable(name = "user_id") long userId, 
			Model model) {
      model.addAttribute("todos",toDoService.getByUserId(userId));
      return "todos-user";
  }

//    @GetMapping("/create/users/{owner_id}")
//    public String create(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @PostMapping("/create/users/{owner_id}")
//    public String create(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{id}/tasks")
//    public String read(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{todo_id}/update/users/{owner_id}")
//    public String update(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @PostMapping("/{todo_id}/update/users/{owner_id}")
//    public String update(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{todo_id}/delete/users/{owner_id}")
//    public String delete(//add needed parameters) {
//                         // ToDo
//        return " ";
//    }
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

package com.softserve.itacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.itacademy.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/delete/{user_id}")
	public String delete(@PathVariable(name = "user_id")long userId) {
		userService.delete(userId);
		return "redirect:/home";
		}
//    @GetMapping("/create")
//    public String create(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @PostMapping("/create")
//    public String create(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{id}/read")
//    public String read(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{id}/update")
//    public String update(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//
//    
//
//    @GetMapping("/all")
//    public String getAll(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
}

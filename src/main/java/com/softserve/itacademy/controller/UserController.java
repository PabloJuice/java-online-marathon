package com.softserve.itacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.RoleService;
import com.softserve.itacademy.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("/delete/{user_id}")
	public String delete(@PathVariable(name = "user_id")long userId) {
		userService.delete(userId);
		return "redirect:/home";
		}
	
	@GetMapping("/update/{user_id}")
	public String update(@PathVariable(name = "user_id")long userId, Model model) {
		model.addAttribute("user", userService.readById(userId));
		model.addAttribute("roles", roleService.getAll());
		return "update-user";
	}
	
	@PostMapping("/updateUser")
	public String update(@ModelAttribute(name = "user") User user) {
		userService.update(user);
		return "redirect:/home";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleService.getAll());
		return "create-user";
	}
	
	@PostMapping("/createUser")
	public String create(@ModelAttribute(name = "user") User user) {
		userService.create(user);
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

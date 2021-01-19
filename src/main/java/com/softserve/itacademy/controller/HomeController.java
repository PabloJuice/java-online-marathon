package com.softserve.itacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.softserve.itacademy.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
    UserService userService;

    @GetMapping({"/", "home"})
    public String home(Model model) {
    	model.addAttribute("users", userService.getAll());
        return "home";
    }
}

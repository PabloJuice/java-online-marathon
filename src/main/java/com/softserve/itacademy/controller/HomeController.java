package com.softserve.itacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //ToDo

    @GetMapping({"/", "home"})
    public String home() {
        //ToDo
        return "home";
    }
}

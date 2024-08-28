package ru.my.springcourse.FirstRestApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class FirstRestController {
    @GetMapping
    @ResponseBody
    public String hello() {
        return "Hello world!";
    }
}

package ru.my.springcourse.FirstRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.my.springcourse.FirstRestApp.models.Person;
import ru.my.springcourse.FirstRestApp.services.PeopleService;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public List<Person> people() {
        return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public Person person(@PathVariable("id") Integer id) {
        return peopleService.findById(id);
    }
}

package ru.my.springcourse.FirstRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.my.springcourse.FirstRestApp.models.Person;
import ru.my.springcourse.FirstRestApp.services.PeopleService;
import ru.my.springcourse.FirstRestApp.util.PersonErrorResponse;
import ru.my.springcourse.FirstRestApp.util.PersonNotFoundException;

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

    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handleResponse(PersonNotFoundException notFoundException) {
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(
                "Person with this id wasn't found!",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(personErrorResponse, HttpStatus.NOT_FOUND);
    }
}

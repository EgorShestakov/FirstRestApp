package ru.my.springcourse.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.my.springcourse.FirstRestApp.models.Person;
import ru.my.springcourse.FirstRestApp.services.PeopleService;
import ru.my.springcourse.FirstRestApp.util.PersonErrorResponse;
import ru.my.springcourse.FirstRestApp.util.PersonNotCreatedException;
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

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            throw new PersonNotCreatedException(errorMsg.toString());
        }

        peopleService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleResponse(PersonNotFoundException notFoundException) {
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(
                "Person with this id wasn't found!",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(personErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleResponse(PersonNotCreatedException notCreatedException) {
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(
                notCreatedException.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(personErrorResponse, HttpStatus.BAD_REQUEST);
    }
}

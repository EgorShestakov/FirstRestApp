package ru.my.springcourse.FirstRestApp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PersonDTO {
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters!")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0!")
    @Column(name = "age")
    private int age;

    @Email
    @NotEmpty(message = "Email should not be empty!")
    @Column(name = "email")
    private String email;

    public PersonDTO(){}
    public PersonDTO(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

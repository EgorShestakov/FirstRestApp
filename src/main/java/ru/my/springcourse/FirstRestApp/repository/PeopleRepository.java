package ru.my.springcourse.FirstRestApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.my.springcourse.FirstRestApp.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}

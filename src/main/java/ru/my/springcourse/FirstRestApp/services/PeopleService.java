package ru.my.springcourse.FirstRestApp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.my.springcourse.FirstRestApp.models.Person;
import ru.my.springcourse.FirstRestApp.repository.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(Integer id) {
        return peopleRepository.findById(id).orElse(null);
    }
}

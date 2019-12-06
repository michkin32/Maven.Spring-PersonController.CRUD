package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class PersonController {

    private PersonRepository personRepository;
    @Autowired
    public PersonController(PersonRepository repository) {
        this.personRepository = repository;
    }

    @PostMapping("/people")
    public Person createPerson(@RequestBody Person p){
        return personRepository.save(p);
    }



    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable int id){
        return personRepository.findOne((long) id);
    }

    @GetMapping("/people")
    public List<Person> getPersonList(){
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(people::add);
        return people;
    }

    @PutMapping("/people/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person p){
        Person originalPerson = personRepository.findOne(id);
        originalPerson.setFirstName(p.getFirstName());
        originalPerson.setLastName(p.getLastName());
        return personRepository.save(originalPerson);

    }

    @DeleteMapping("/people/{id}")
    public void DeletePerson(@PathVariable int id){
    personRepository.delete((long) id);
    }

}

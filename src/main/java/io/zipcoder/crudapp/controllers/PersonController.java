package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import io.zipcoder.crudapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class PersonController {

    private PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person p){
        return new ResponseEntity<>(personService.create(p), HttpStatus.CREATED);
    }



    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id){
        if(personService.exist((long)id)){
        return new ResponseEntity<>(personService.show((long)id), HttpStatus.OK);
    }else{
            return new ResponseEntity<>(personService.show((long)id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getPersonList(){
        return new ResponseEntity<>(personService.index(), HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person p){
        if(personService.exist(id)){
            return new ResponseEntity<>(personService.update(id, p), HttpStatus.OK);
        }else{
            return createPerson(p);
        }
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> DeletePerson(@PathVariable int id){
        personService.delete((long)id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}

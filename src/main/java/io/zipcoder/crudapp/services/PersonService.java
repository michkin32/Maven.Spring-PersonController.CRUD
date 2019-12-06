package io.zipcoder.crudapp.services;

import io.zipcoder.crudapp.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository repository;
    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Boolean exist(Long id){
        if(!(repository.findOne(id) == null)){
            return true;
        }else{
            return false;
        }
    }
    public Iterable<Person> index() {
        return repository.findAll();
    }

    public Person show(Long id) {
        return repository.findOne(id);
    }

    public Person create(Person p) {
        return repository.save(p);
    }

    public Person update(Long id, Person p) {
        Person originalPerson = repository.findOne(id);
        originalPerson.setFirstName(p.getFirstName());
        originalPerson.setLastName(p.getLastName());
        return repository.save(originalPerson);
    }

    public Boolean delete(Long id) {
        repository.delete(id);
        return true;
    }
}

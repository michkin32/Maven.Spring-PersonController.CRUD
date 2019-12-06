package io.zipcoder.crudapp.repositories;

import io.zipcoder.crudapp.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}

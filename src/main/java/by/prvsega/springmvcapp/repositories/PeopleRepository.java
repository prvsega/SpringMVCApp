package by.prvsega.springmvcapp.repositories;

import by.prvsega.springmvcapp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer > {

    List<Person> findByName(String name);


}

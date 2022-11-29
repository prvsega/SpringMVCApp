package by.prvsega.springmvcapp.dao;

import by.prvsega.springmvcapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int maxId() {
        Integer i = jdbcTemplate.queryForObject("SELECT MAX(ID) FROM Person", Integer.class);

        if (i == null){
            i = 0;
        }

            return i;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {

        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);

    }

    public void save(Person person) {
//        i = new PersonDAO(new JdbcTemplate()).maxId();
        jdbcTemplate.update("INSERT INTO Person VALUES (?, ?, ?, ?)", maxId() + 1, person.getName(), person.getAge(), person.getEmail());


    }

    public void update(int id, Person updatedPerson) {


        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);


    }

    public void delete(int id) {
//        Person personToBeDelete = show(id);
//        people.remove(personToBeDelete.getId());
        /*        people.removeIf(p -> p.getId() == id);*/
        jdbcTemplate.update("DELETE FROM  Person WHERE id=?", id);

    }

    public List<Person> search(String string){

        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{string} , new BeanPropertyRowMapper<>(Person.class));

    }
}

package by.prvsega.springmvcapp.dao;

import by.prvsega.springmvcapp.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {

        Session session = sessionFactory.getCurrentSession();

        List<Person> list = session.createQuery("select p from Person p", Person.class).getResultList();

        return list;
    }

    @Transactional(readOnly = true)
    public Person show(int id) {

        Session session = sessionFactory.getCurrentSession();

        return session.get(Person.class, id);

    }
//    @Transactional(readOnly = true)
//    public Person show(String str){
//
//        Session session = sessionFactory.getCurrentSession();
//
//        Person person = session.createQuery("select p from Person p where email = '?'", new Object(){str} ,Person.class)
//
//
//    return null;
//
//    }


    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);

    }

    @Transactional()
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);

        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
        person.setEmail(updatedPerson.getEmail());
        person.setAddress(updatedPerson.getAddress());

        session.save(person);

    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        session.remove(session.get(Person.class,id));


    }


}

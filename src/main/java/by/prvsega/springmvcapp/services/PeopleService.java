package by.prvsega.springmvcapp.services;

import by.prvsega.springmvcapp.models.Person;
import by.prvsega.springmvcapp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // по умолчанию над всеми методами,
                                // но если у метода свой @Transactional -> он приоритетный
public class PeopleService {

    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);

//        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person){

        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson){

        updatePerson.setId(id);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);

    }

}

package by.prvsega.springmvcapp.util;

import by.prvsega.springmvcapp.models.Person;
import by.prvsega.springmvcapp.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

//        if (personDAO.show(person.getEmail()) != null){
//            errors.rejectValue("email", "", "This email isn't unique");
//        }


    }
}

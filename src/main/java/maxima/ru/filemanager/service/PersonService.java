package maxima.ru.filemanager.service;

import maxima.ru.filemanager.dto.PersonDto;
import maxima.ru.filemanager.exception.RepositoryException;
import maxima.ru.filemanager.mapper.PersonMapper;
import maxima.ru.filemanager.model.Person;
import maxima.ru.filemanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;
    private PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPersonById(Long id) {
        Person Person = null;
        return Optional.of(personRepository.save(person()))
                .map(personMapper::toDTO)
                .orElseThrow(() -> RepositoryException.builder()
                        .build());

        //personRepository.findById(id).orElse(null);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public PersonDto createPersonDto(String fullName, String gender, String role) {
        Person person = setPerson(fullName, gender, role);
        return Optional.of(personRepository.save(personDto))
                .flatMap(personMapper::toDTO)
                .orElseThrow(() -> RepositoryException.builder().build());
    }
        //return personRepository.save(newPerson);

    public Person updatePerson(Long id, Person updatedPerson) {
        Person existingPerson = personRepository.findById(id).orElse(null);
        if (existingPerson != null) {
            updatedPerson.setId(existingPerson.getId());
            return personRepository.save(updatedPerson);
        } else {
            return null;
        }
    }

    public boolean deletePerson(Long id) {
        Person existingPerson = personRepository.findById(id).orElse(null);
        if (existingPerson != null) {
            personRepository.delete(existingPerson);
            return true;
        } else {
            return false;
        }
    }
}
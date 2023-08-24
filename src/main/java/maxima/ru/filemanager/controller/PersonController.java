package maxima.ru.filemanager.controller;

import maxima.ru.filemanager.dto.PersonDto;
import maxima.ru.filemanager.model.Person;
import maxima.ru.filemanager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        if (!persons.isEmpty()) {
            return ResponseEntity.ok(persons);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Person> createPerson(@RequestBody Person newPerson) {
        Person createdPerson = personService.createPerson(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        Person person = personService.updatePerson(id, updatedPerson);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean deleted = personService.deletePerson(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
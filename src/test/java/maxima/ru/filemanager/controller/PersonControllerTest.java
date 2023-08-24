package maxima.ru.filemanager.controller;

import maxima.ru.filemanager.BasicTestContainer;
import maxima.ru.filemanager.model.File;
import maxima.ru.filemanager.model.Person;
import maxima.ru.filemanager.repository.FileRepository;
import maxima.ru.filemanager.repository.PersonRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonControllerTest extends BasicTestContainer {

    @Autowired
    private PersonRepository personRepository;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getPersonByIdTest() throws Exception {
        Person build = Person.builder()
                .id(1L)
                .fullName("Dima")
                .gender("Male")
                .role("Developer")
                .build();
        personRepository.save(build);
        String contentAsString = mvc.perform(MockMvcRequestBuilders.get("/api/persons")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Person person = objectMapper.readValue(contentAsString, Person.class);
        Assertions.assertEquals(build.getId(), person.getId());
        Assertions.assertEquals(build.getFullName(), person.getFullName());
        Assertions.assertEquals(build.getGender(), person.getGender());
        Assertions.assertEquals(build.getRole(), person.getRole());
    }

    @Test
    public void createPersonTest() throws Exception {
        Person build = Person.builder()
                .id(1L)
                .fullName("Dima")
                .gender("Male")
                .role("Developer")
                .build();
        personRepository.save(build);
        mvc.perform(MockMvcRequestBuilders.post("/api/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(build)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updatePersonTest() throws Exception {
        Person build = Person.builder()
                .id(1L)
                .fullName("Dima")
                .gender("Male")
                .role("Developer")
                .build();
        personRepository.save(build);
        //новые данные для перезаписи
        build.setFullName("John");
        build.setGender("Female");
        build.setRole("Manager");
        personRepository.save(build);
        mvc.perform(MockMvcRequestBuilders.post("/api/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(build)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deletePersonTest() throws Exception {
        Person build = Person.builder()
                .id(1L)
                .fullName("Dima")
                .gender("Male")
                .role("Developer")
                .build();
        personRepository.save(build);
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/persons" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

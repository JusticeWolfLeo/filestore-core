package maxima.ru.filemanager.controller;


import maxima.ru.filemanager.BasicTestContainer;
import maxima.ru.filemanager.model.File;
import maxima.ru.filemanager.repository.FileRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class FileControllerTest extends BasicTestContainer {


    @Autowired
    private FileRepository fileRepository;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getFileByIdTest() throws Exception {
        File build = File.builder()
                .author(123L)
                .describe("1234")
                .destiny("test")
                .build();
        File save = fileRepository.save(build);
        String contentAsString = mvc.perform(MockMvcRequestBuilders.get("/file/" + save.getId()) //..................................
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        File file = objectMapper.readValue(contentAsString, File.class);
        Assertions.assertEquals(build.getId(), file.getId());
        Assertions.assertEquals(build.getAuthor(), file.getAuthor());
        Assertions.assertEquals(build.getDescribe(), file.getDescribe());
    }

    @Test
    public void createFileTest() throws Exception {
        File build = File.builder()
                .id(1L)
                .author(123L)
                .describe("1234")
                .destiny("test")
                .build();
        fileRepository.save(build);
        mvc.perform(MockMvcRequestBuilders.post("/file")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(build)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateFileTest() throws Exception {
        File build = File.builder()
                .id(1L)
                .author(123L)
                .describe("TestDescribe")
                .destiny("TestDestiny")
                .build();
        fileRepository.save(build);
        //новые данные для перезаписи
        build.setAuthor(123L);
        build.setDescribe("New describe");
        build.setDestiny("New Destiny");
        fileRepository.save(build);
        mvc.perform(MockMvcRequestBuilders.post("/file")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(build)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteFileTest() throws Exception {
        File build = File.builder()
                .id(1L)
                .author(123L)
                .describe("TestDescribe")
                .destiny("TestDestiny")
                .build();
        fileRepository.save(build);
        mvc.perform(MockMvcRequestBuilders
                        .delete("/file/ + 1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.dto.create.EventCategoryCreateDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventCategoryControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.2")
            .withInitScript("create.sql");

    @BeforeAll
    static void setUp()
    {
        postgres.start();
    }

    @AfterAll
    static void tearDown()
    {
        postgres.stop();
    }

    @ParameterizedTest
    @WithMockUser
    @Order(1)
    @CsvSource({
            "6a646d1c-5d76-4629-9107-1ec2c25c7753",
            "2a171f32-8d35-43f3-b3f8-bc1fdac52f4c"
    })
    public void testGetById(String id) throws Exception
    {
        mockMvc.perform(get("/api/event-category/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(2)
    @CsvSource({
            "6a646d1c-5d76-4629-9107-1ec2c25c7753, Name3",
            "2a171f32-8d35-43f3-b3f8-bc1fdac52f4c, Name2"
    })
    public void testChangeName(String id, String newName) throws Exception
    {
        mockMvc.perform(put("/api/event-category/change/name")
                        .param("id", id)
                        .param("newName", newName)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/event-category/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(newName));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(3)
    @CsvSource({
            "6a646d1c-5d76-4629-9107-1ec2c25c7753, Desc1",
            "2a171f32-8d35-43f3-b3f8-bc1fdac52f4c, Desc2"
    })
    public void testChangeDescription(String id, String newDescription) throws Exception
    {
        mockMvc.perform(put("/api/event-category/change/description")
                        .param("id", id)
                        .param("newDescription", newDescription)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/event-category/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(newDescription));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(4)
    @CsvSource({
            "6a646d1c-5d76-4629-9107-1ec2c25c7753",
            "2a171f32-8d35-43f3-b3f8-bc1fdac52f4c"
    })
    public void testDelete(String id) throws Exception
    {
        mockMvc.perform(delete("/api/event-category/delete")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/event-category/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    @Order(5)
    public void testCreate() throws Exception
    {
        var category = EventCategoryCreateDto.builder()
                .name("Name")
                .description("desc")
                .build();

        var m = new ObjectMapper();
        var obj = m.writeValueAsString(category);

        System.out.println(obj);

        mockMvc.perform(post("/api/event-category/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obj)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));
    }
}
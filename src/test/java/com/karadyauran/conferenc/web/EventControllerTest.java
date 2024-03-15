package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.dto.create.EventCreateDto;
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

import java.sql.Timestamp;
import java.util.UUID;

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
class EventControllerTest
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

    @Test
    @Order(1)
    public void testGetAll() throws Exception
    {
        mockMvc.perform(get("/api/event/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].id").exists());
    }

    @ParameterizedTest
    @WithMockUser
    @Order(2)
    @CsvSource({
            "Convention Center",
            "Tech Hub"
    })
    public void testGetByLocation(String location) throws Exception
    {
        mockMvc.perform(get("/api/event/find/location")
                        .param("location", location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].location").value(location));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(3)
    @CsvSource({
            "Tech Conference 2024",
            "Workshop on AI"
    })
    public void testGetByTitle(String title) throws Exception
    {
        mockMvc.perform(get("/api/event/find/title")
                .param("title", title))
                .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value(title));
    }


    @ParameterizedTest
    @WithMockUser
    @Order(2)
    @CsvSource({
            "7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1",
            "f1a60e4f-ae7e-447d-a08a-2d97a356b4e7"
    })
    public void testGetById(String id) throws Exception
    {
        mockMvc.perform(get("/api/event/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @WithMockUser
    @Order(3)
    public void testGetByUserId() throws Exception
    {
        mockMvc.perform(get("/api/event/find/user")
                        .param("user", "550e8400-e29b-41d4-a716-446655440000")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].organizer.id").value("550e8400-e29b-41d4-a716-446655440000"));
    }

    @Test
    @WithMockUser
    @Order(4)
    public void testCreateEvent() throws Exception
    {
        var event = EventCreateDto.builder()
                .organizerId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"))
                .title("New title")
                .description("Description")
                .start(Timestamp.valueOf("2024-03-12 10:00:00"))
                .end(Timestamp.valueOf("2024-03-12 13:00:00"))
                .location("Location")
                .capacity(12)
                .isPublic(Boolean.FALSE)
                .build();

        var m = new ObjectMapper();
        var obj = m.writeValueAsString(event);

        mockMvc.perform(post("/api/event/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obj)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));

    }

    @ParameterizedTest
    @WithMockUser
    @Order(5)
    @CsvSource({
            "7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1, title1",
            "f1a60e4f-ae7e-447d-a08a-2d97a356b4e7, title2"
    })
    public void testChangeTitle(String id, String newTitle) throws Exception
    {
        mockMvc.perform(put("/api/event/change/title")
                        .param("id", id)
                        .param("newTitle", newTitle)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/event/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(jsonPath("$.title").value(newTitle));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(6)
    @CsvSource({
            "7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1, desc1",
            "f1a60e4f-ae7e-447d-a08a-2d97a356b4e7, desc2"
    })
    public void testChangeDescription(String id, String newDescription) throws Exception
    {
        mockMvc.perform(put("/api/event/change/description")
                        .param("id", id)
                        .param("newDescription", newDescription)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/event/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(jsonPath("$.description").value(newDescription));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(7)
    @CsvSource({
            "7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1, 2024-03-12 09:00:00.000000000",
            "f1a60e4f-ae7e-447d-a08a-2d97a356b4e7, 2024-03-12 09:00:00.000000000"
    })
    public void testChangeStart(String id, String newStart) throws Exception
    {
        mockMvc.perform(put("/api/event/change/time/start")
                        .param("id", id)
                        .param("newStart", newStart)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @WithMockUser
    @Order(8)
    @CsvSource({
            "7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1, 2024-04-12 09:00:00.000000000",
            "f1a60e4f-ae7e-447d-a08a-2d97a356b4e7, 2024-04-12 09:00:00.000000000"
    })
    public void testChangeEnd(String id, String newEnd) throws Exception
    {
        mockMvc.perform(put("/api/event/change/time/end")
                        .param("id", id)
                        .param("newEnd", newEnd)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @WithMockUser
    @Order(9)
    @CsvSource({
            "7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1, Hamburg",
            "f1a60e4f-ae7e-447d-a08a-2d97a356b4e7, San-Francisco"
    })
    public void testChangeLocation(String id, String newLocation) throws Exception
    {
        mockMvc.perform(put("/api/event/change/location")
                        .param("id", id)
                        .param("newLocation", newLocation)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/event/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(jsonPath("$.location").value(newLocation));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(10)
    @CsvSource({
            "7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1, 23",
            "f1a60e4f-ae7e-447d-a08a-2d97a356b4e7, 323"
    })
    public void testChangeCapacity(String id, String newCapacity) throws Exception
    {
        mockMvc.perform(put("/api/event/change/capacity")
                        .param("id", id)
                        .param("newCapacity", newCapacity)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/event/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(jsonPath("$.capacity").value(newCapacity));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(11)
    @CsvSource({
            "7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1, 6a646d1c-5d76-4629-9107-1ec2c25c7753",
            "7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1, 2a171f32-8d35-43f3-b3f8-bc1fdac52f4c"
    })
    public void testAddCategory(String id, String category) throws Exception
    {
        mockMvc.perform(post("/api/event/add-category")
                        .param("id", id)
                        .param("categoryId", category)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @WithMockUser
    @Order(12)
    @CsvSource({
            "7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1",
            "f1a60e4f-ae7e-447d-a08a-2d97a356b4e7"
    })
    public void testDelete(String id) throws Exception
    {
        mockMvc.perform(delete("/api/event/delete")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/event/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }
}
package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.dto.create.EventCreateDto;
import com.karadyauran.conferenc.dto.create.SessionCreateDto;
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
import static org.junit.jupiter.api.Assertions.*;
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
class SessionControllerTest
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
            "a7c518d1-f074-4095-977d-3c123e6b13cc",
            "f865cd7e-0381-4f22-b624-af27dbf48d17"
    })
    public void testGetById(String id) throws Exception
    {
        mockMvc.perform(get("/api/session/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(2)
    @CsvSource({
            "a7c518d1-f074-4095-977d-3c123e6b13cc, 2024-03-12 09:00:00.000000000",
            "f865cd7e-0381-4f22-b624-af27dbf48d17, 2024-03-12 09:00:00.000000000"
    })
    public void testChangeStart(String id, String newStart) throws Exception
    {
        mockMvc.perform(put("/api/session/change/time/start")
                        .param("id", id)
                        .param("newStart", newStart)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @WithMockUser
    @Order(3)
    @CsvSource({
            "a7c518d1-f074-4095-977d-3c123e6b13cc, 2024-03-12 09:00:00.000000000",
            "f865cd7e-0381-4f22-b624-af27dbf48d17, 2024-03-12 09:00:00.000000000"
    })
    public void testChangeEnd(String id, String newEnd) throws Exception
    {
        mockMvc.perform(put("/api/session/change/time/end")
                        .param("id", id)
                        .param("newEnd", newEnd)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @WithMockUser
    @Order(4)
    @CsvSource({
            "a7c518d1-f074-4095-977d-3c123e6b13cc, Speaker1",
            "f865cd7e-0381-4f22-b624-af27dbf48d17, Speaker2"
    })
    public void testChangeSpeaker(String id, String newSpeaker) throws Exception
    {
        mockMvc.perform(put("/api/session/change/speaker")
                        .param("id", id)
                        .param("newSpeaker", newSpeaker)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/session/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(jsonPath("$.speaker").value(newSpeaker));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(5)
    @CsvSource({
            "a7c518d1-f074-4095-977d-3c123e6b13cc, Location1",
            "f865cd7e-0381-4f22-b624-af27dbf48d17, Location2"
    })
    public void testChangeLocation(String id, String newLocation) throws Exception
    {
        mockMvc.perform(put("/api/session/change/location")
                        .param("id", id)
                        .param("newLocation", newLocation)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/session/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(jsonPath("$.location").value(newLocation));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(6)
    @CsvSource({
            "a7c518d1-f074-4095-977d-3c123e6b13cc",
            "f865cd7e-0381-4f22-b624-af27dbf48d17"
    })
    public void testDelete(String id) throws Exception
    {
        mockMvc.perform(delete("/api/session/delete")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/session/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    @Order(7)
    public void testCreateSession() throws Exception
    {
        var s = SessionCreateDto.builder()
                .userId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"))
                .eventId(UUID.fromString("7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1"))
                .title("Title")
                .start(Timestamp.valueOf("2024-03-12 10:00:00"))
                .end(Timestamp.valueOf("2024-03-12 13:00:00"))
                .speaker("Speacker1")
                .location("Location")
                .status("Started")
                .build();

        var m = new ObjectMapper();
        var obj = m.writeValueAsString(s);

        mockMvc.perform(post("/api/session/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obj)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));

    }
}
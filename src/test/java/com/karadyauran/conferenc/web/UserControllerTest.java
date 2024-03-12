package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.dto.create.UserCreateDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest
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
    @ValueSource(
            strings = {
                    "550e8400-e29b-41d4-a716-446655440000",
                    "fa163f16-71f8-4d52-a8c3-215df450a285"
            }
    )
    public void testGetById(String userId) throws Exception
    {
        mockMvc.perform(get("/api/user/byid")
                        .param("id", userId)
                        .with(csrf()))
                .andExpect(jsonPath("$.id").value(userId));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(2)
    @CsvSource({
            "john_doe",
            "jane_smith"
    })
    public void testGetByUsername(String username) throws Exception
    {
        mockMvc.perform(get("/api/user/username/" + username))
                .andExpect(jsonPath("$.username").value(username));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(3)
    @CsvSource({
            "550e8400-e29b-41d4-a716-446655440000, username1",
            "fa163f16-71f8-4d52-a8c3-215df450a285, username2"
    })
    public void testChangeUsername(String userId, String newUsername) throws Exception
    {
        mockMvc.perform(put("/api/user/change/username")
                        .param("id", userId)
                        .param("username", newUsername)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/user/byid")
                        .param("id", userId)
                        .with(csrf()))
                .andExpect(jsonPath("$.username").value(newUsername));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(5)
    @CsvSource({
            "550e8400-e29b-41d4-a716-446655440000",
            "fa163f16-71f8-4d52-a8c3-215df450a285"
    })
    public void testDeleteUsers(String userId) throws Exception
    {
        mockMvc.perform(delete("/api/user/delete")
                        .param("id", userId)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/byid")
                        .param("id", userId)
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @WithMockUser
    @Order(6)
    @CsvSource({
            "username1, username1@gmail.com, 123123, ATTENDEE",
            "username2, username2@gmail.com, 123123, ORGANIZER",
            "username3, username3@gmail.com, 123123, ATTENDEE",
            "username4, username4@gmail.com, 123123, ATTENDEE",
            "username5, username5@gmail.com, 123123, ORGANIZER",
            "username6, username6@gmail.com, 123123, ATTENDEE",
            "username7, username71@gmail.com, 123123, ORGANIZER",
            "username8, username8@gmail.com, 123123, ATTENDEE",
            "username9, username9@gmail.com, 123123, ORGANIZER",
            "username0, username01@gmail.com, 123123, ORGANIZER"
    })
    public void testRegister(String username, String email, String password, String role) throws Exception
    {
        var user = UserCreateDto.builder()
                .username(username)
                .email(email)
                .password(password)
                .role(role)
                .build();

        var m = new ObjectMapper();
        var obj = m.writeValueAsString(user);

        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obj))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/user/username/" + username))
                .andExpect(jsonPath("$.username").value(username));
    }
}
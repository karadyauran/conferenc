package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.dto.create.BookingCreateDto;
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
class BookingControllerTest
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
            "3fde6a02-6824-447f-a621-5f103bf33b76",
            "ce25d345-1c81-4e9b-8114-8a7548c8a5b3"
    })
    public void testGetById(String id) throws Exception
    {
        mockMvc.perform(get("/api/booking/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(2)
    @CsvSource({
            "fa163f16-71f8-4d52-a8c3-215df450a285"
    })
    public void testGetByUserId(String id) throws Exception
    {
        mockMvc.perform(get("/api/booking/find/user")
                        .param("user", id)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @WithMockUser
    @Order(3)
    @CsvSource({
            "3fde6a02-6824-447f-a621-5f103bf33b76, PENDING",
            "ce25d345-1c81-4e9b-8114-8a7548c8a5b3, CONFIRMED"
    })
    public void testChangeStatus(String id, String status) throws Exception
    {
        mockMvc.perform(put("/api/booking/change/status")
                        .param("id", id)
                        .param("newStatus", status)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/booking/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(status));
    }

    @ParameterizedTest
    @WithMockUser
    @Order(4)
    @CsvSource({
            "3fde6a02-6824-447f-a621-5f103bf33b76",
            "ce25d345-1c81-4e9b-8114-8a7548c8a5b3"
    })
    public void testDelete(String id) throws Exception
    {
        mockMvc.perform(delete("/api/booking/delete")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/booking/find/id")
                        .param("id", id)
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    @Order(5)
    public void testCreateBooking() throws Exception
    {
        var booking = BookingCreateDto.builder()
                .eventId(UUID.fromString("7d6bb0d2-fc4c-4879-b15e-df7a145ce9a1"))
                .userId(UUID.fromString("fa163f16-71f8-4d52-a8c3-215df450a285"))
                .build();

        var m = new ObjectMapper();
        var obj = m.writeValueAsString(booking);

        System.out.println(obj);

        mockMvc.perform(post("/api/booking/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obj)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));
    }
}
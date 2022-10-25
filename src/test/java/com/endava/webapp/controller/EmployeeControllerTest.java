package com.endava.webapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeControllerTest extends BaseIT {

    @Test
    void shouldReturnOk_whenSavingNewEmployee() throws Exception {
        mockMvc.perform(post("/employees")
                        .content(readJsonFromFile("/mock-request/employee_create_request"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(readJsonFromFile("/expected-response/employee_created")));
    }

    @Test
    void shouldReturnOk_whenGettingAllEmployees() throws Exception {
        mockMvc.perform(get("/employees?offset=2&limit=3"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOk_whenGettingExistingEmployeeById() throws Exception {
        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFound_whenGettingNonExistingEmployeeById() throws Exception {
        mockMvc.perform(get("/employees/1111"))
                .andExpect(status().is(404));
    }

    // 500 - server error currently
    @Test
    void shouldReturnBadRequest_whenGettingEmployeeInWrongFormat() throws Exception {
        mockMvc.perform(get("/employees/string"))
                .andExpect(status().is(400));
    }

    @Test
    void shouldReturnOk_whenUpdatingExistingEmployee() throws Exception {
        mockMvc.perform(put("/employees/7")
                        .content(readJsonFromFile("/mock-request/employee_update_request"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(readJsonFromFile("/expected-response/employee_updated")));
    }

}

package com.endava.webapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DepartmentControllerTest extends BaseIT {

    @Test
    void shouldReturnOk_whenGettingAllDepartments() throws Exception {
        mockMvc.perform(get("/departments?offset=2&limit=3"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOk_whenGettingExistingDepartmentById() throws Exception {
        mockMvc.perform(get("/departments/2"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFound_whenGettingNonExistingDepartmentById() throws Exception {
        mockMvc.perform(get("/departments/1111"))
                .andExpect(status().is(404));
    }

    @Test
    void shouldReturnBadRequest_whenGettingDepartmentInWrongFormat() throws Exception {
        mockMvc.perform(get("/departments/string"))
                .andExpect(status().is(400));
    }

    @Test
    void shouldReturnOk_whenSavingNewDepartment() throws Exception {
        mockMvc.perform(post("/departments")
                        .content(readJsonFromFile("/mock-request/department_create_request"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(readJsonFromFile("/expected-response/department_created")));
    }

    @Test
    void shouldReturnBadRequest_whenSavingInvalidDepartment() throws Exception {
        mockMvc.perform(post("/employees")
                        .content(readJsonFromFile("/mock-request/department_create_invalid_request"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    void shouldReturnOk_whenUpdatingExistingDepartment() throws Exception {
        mockMvc.perform(put("/departments/7")
                        .content(readJsonFromFile("/mock-request/department_update_request"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(readJsonFromFile("/expected-response/department_updated")));
    }

    @Test
    void shouldReturnNotFound_whenUpdatingNonExistingDepartment() throws Exception {
        mockMvc.perform(put("/departments/7777")
                        .content(readJsonFromFile("/mock-request/department_update_request"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void shouldReturnBadRequest_whenUpdatingDepartmentWithInvalidData() throws Exception {
        mockMvc.perform(put("/departments/7")
                        .content(readJsonFromFile("/mock-request/department_update_invalid_request"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
}

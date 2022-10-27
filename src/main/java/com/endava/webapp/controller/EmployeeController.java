package com.endava.webapp.controller;

import com.endava.webapp.dto.EmployeeRequest;
import com.endava.webapp.dto.EmployeeResponse;
import com.endava.webapp.dto.PageableResponse;
import com.endava.webapp.dto.mapper.EmployeeMapper;
import com.endava.webapp.model.Employee;
import com.endava.webapp.service.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Validated(EmployeeRequest.class)
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private final EmployeeMapper mapper;

    @PostMapping
    public ResponseEntity<EmployeeResponse> addEmployee(@Validated @RequestBody EmployeeRequest request) {
        val requestToEmployee = mapper.requestToEmployee(request);
        employeeService.save(requestToEmployee);
        val employeeResponse = mapper.employeeToResponse(requestToEmployee);
        return ResponseEntity.ok(employeeResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeByID(@PathVariable final Integer id) {
        val employee = employeeService.getById(id);
        return ResponseEntity.ok(mapper.employeeToResponse(employee));
    }

    @GetMapping
    public ResponseEntity<PageableResponse<EmployeeResponse>> getAllEmployees(
            @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        val employees = employeeService.getAllEmployees(offset, limit);
        val mappedEmployees = employees.stream().
                map(mapper::employeeToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(
                PageableResponse.<EmployeeResponse>builder()
                        .data(mappedEmployees)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Integer id, @Validated @RequestBody EmployeeRequest employee) {
        Employee updatedEmployee = employeeService.update(id, employee);
        return ResponseEntity.ok(mapper.employeeToResponse(updatedEmployee));
    }
}

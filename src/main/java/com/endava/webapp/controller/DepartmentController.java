package com.endava.webapp.controller;

import com.endava.webapp.dto.DepartmentRequest;
import com.endava.webapp.dto.DepartmentResponse;
import com.endava.webapp.dto.PageableResponse;
import com.endava.webapp.dto.mapper.DepartmentMapper;
import com.endava.webapp.model.Department;
import com.endava.webapp.service.DepartmentServiceImpl;
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
@RequestMapping("/departments")
@RequiredArgsConstructor
@Validated(DepartmentRequest.class)
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;
    private final DepartmentMapper mapper;

    @PostMapping
    public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody DepartmentRequest request) {
        val requestToDepartment = mapper.requestToDepartment(request);
        departmentService.save(requestToDepartment);
        val departmentResponse = mapper.departmentToResponse(requestToDepartment);
        return ResponseEntity.ok(departmentResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable final Integer id) {
        Department department = departmentService.getById(id);
        return ResponseEntity.ok(mapper.departmentToResponse(department));
    }

    @GetMapping
    public ResponseEntity<PageableResponse<DepartmentResponse>> getAllDepartments(
            @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        val departments = departmentService.getAllDepartments(offset, limit);
        val mappedDepartments = departments.stream()
                .map(mapper::departmentToResponse).collect(Collectors.toList());
        return ResponseEntity.ok(
                PageableResponse.<DepartmentResponse>builder()
                        .data(mappedDepartments)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@PathVariable Integer id, @RequestBody DepartmentRequest department) {
        Department updatedDepartment = departmentService.update(id, department);
        return ResponseEntity.ok(mapper.departmentToResponse(updatedDepartment));
    }
}

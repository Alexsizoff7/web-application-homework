package com.endava.webapp.service;

import com.endava.webapp.dto.DepartmentRequest;
import com.endava.webapp.model.Department;

import java.util.List;

public interface DepartmentService {
    Department getById(final Integer id);

    List<Department> getAllDepartments(int offset, int limit);

    Department update(final Integer id, DepartmentRequest department);

    Department save(Department department);
}

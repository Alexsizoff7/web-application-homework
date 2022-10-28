package com.endava.webapp.service;

import com.endava.webapp.dto.EmployeeRequest;
import com.endava.webapp.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getById(final Integer id);

    Employee update(final Integer id, EmployeeRequest employee);

    List<Employee> getAllEmployees(int offset, int limit);

    Employee save(Employee employee);
}

package com.endava.webapp.service;

import com.endava.webapp.dto.EmployeeRequest;
import com.endava.webapp.exception.ResourceNotFoundException;
import com.endava.webapp.model.Employee;
import com.endava.webapp.repository.DepartmentRepository;
import com.endava.webapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;


    @Override
    public Employee getById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with given id don't exist"));
    }

    @Override
    public Employee update(Integer id, EmployeeRequest request) {
        Employee currentEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with given id don't exist"));
        currentEmployee.setFirstName(request.getFirstName());
        currentEmployee.setLastName(request.getLastName());
        currentEmployee.setEmail(request.getEmail());
        currentEmployee.setPhoneNumber(request.getPhoneNumber());
        currentEmployee.setSalary(request.getSalary());
        val currentDepartment = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department with given id don't exist"));
        currentEmployee.setDepartment(currentDepartment);
        return employeeRepository.save(currentEmployee);
    }

    @Override
    public List<Employee> getAllEmployees(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return employeeRepository.findAll(pageable).getContent();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


}

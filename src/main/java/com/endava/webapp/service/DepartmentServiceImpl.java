package com.endava.webapp.service;

import com.endava.webapp.dto.DepartmentRequest;
import com.endava.webapp.exception.ResourceNotFoundException;
import com.endava.webapp.model.Department;
import com.endava.webapp.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department getById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with given id don't exist"));
    }

    @Override
    public List<Department> getAllDepartments(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return departmentRepository.findAll(pageable).getContent();
    }

    @Override
    public Department update(final Integer id, DepartmentRequest request) {
        Department currentDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with given id don't exist"));
        currentDepartment.setDepartmentName(request.getDepartmentName());
        currentDepartment.setLocationId(request.getLocationId());
        return departmentRepository.save(currentDepartment);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }
}

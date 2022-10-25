package com.endava.webapp.dto.mapper;

import com.endava.webapp.dto.DepartmentRequest;
import com.endava.webapp.dto.DepartmentResponse;
import com.endava.webapp.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentResponse departmentToResponse(Department source);

    Department requestToDepartment(DepartmentRequest destination);
}

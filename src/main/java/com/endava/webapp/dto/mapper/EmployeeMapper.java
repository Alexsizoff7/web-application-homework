package com.endava.webapp.dto.mapper;

import com.endava.webapp.dto.EmployeeRequest;
import com.endava.webapp.dto.EmployeeResponse;
import com.endava.webapp.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "department.departmentId", target = "departmentId")
    EmployeeResponse employeeToResponse(Employee source);

    @Mapping(source = "departmentId", target = "department.departmentId")
    Employee requestToEmployee(EmployeeRequest destination);
}

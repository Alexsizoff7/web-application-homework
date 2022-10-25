package com.endava.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private Integer departmentId;
    private String email;
    private String phoneNumber;
    private Integer salary;
}

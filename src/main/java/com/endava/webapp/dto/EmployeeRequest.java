package com.endava.webapp.dto;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class EmployeeRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    @Min(1)
    private Integer salary;

    private Integer departmentId;
    private String jobId;
    private Date hireDate;
    private BigDecimal commissionPct;
    private Integer managerId;
}

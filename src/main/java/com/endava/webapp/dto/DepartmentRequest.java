package com.endava.webapp.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DepartmentRequest {

    @NotBlank
    private String departmentName;

    @NotBlank
    private Integer locationId;

    private Integer managerId;
}

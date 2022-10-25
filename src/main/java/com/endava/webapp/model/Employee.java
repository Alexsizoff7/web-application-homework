package com.endava.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Employee {

    @Id
    @SequenceGenerator(name = "employees_seq", sequenceName = "EMPLOYEES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_seq")
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "job_id")
    private String jobId;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "commission_pct")
    private BigDecimal commissionPct;

    @Column(name = "manager_id")
    private Integer managerId;
}
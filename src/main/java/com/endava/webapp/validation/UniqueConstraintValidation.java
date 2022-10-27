package com.endava.webapp.validation;

import com.endava.webapp.exception.UniqueConstraintViolationException;
import com.endava.webapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueConstraintValidation {

    private final EmployeeRepository employeeRepository;

    public void employeeEmailOrPhoneAlreadyExistsValidation(String email, String phoneNumber) {
        if (employeeRepository.existsByEmailOrPhoneNumber(email, phoneNumber)) {
            throw new UniqueConstraintViolationException("Employee's email and phone number must be unique");
        }
    }
}

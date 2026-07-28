package com.example.ems.projection;

/**
 * Exercise 8: class-based (DTO) projection, populated via a constructor
 * expression in a JPQL query (see EmployeeRepository.findAllProjectedDto).
 */
public class EmployeeDto {

    private final String name;
    private final String email;

    public EmployeeDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
}

package com.example.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Exercise 2 & 5. The @NamedQuery is used by the derived repository method
 * name Employee.findByEmailDomain (Exercise 5 - Named Queries).
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Employee.findByEmailDomain",
                query = "SELECT e FROM Employee e WHERE e.email LIKE :domain")
})
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    // Exercise 2: many employees -> one department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String name, String email, Department department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }
}

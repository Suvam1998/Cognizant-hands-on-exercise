package com.example.ems.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * Exercise 8: interface-based projection. Spring Data returns a proxy exposing
 * only these accessors, and @Value builds a derived (SpEL) property.
 */
public interface EmployeeView {

    String getName();

    String getEmail();

    @Value("#{target.name + ' <' + target.email + '>'}")
    String getDisplay();
}

package com.digitalnurture.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}

@RestController
@RequestMapping("/customers")
class CustomerController {

    @GetMapping
    public List<Map<String, Object>> all() {
        return List.of(
                Map.of("id", 1, "name", "Alice"),
                Map.of("id", 2, "name", "Bob"));
    }

    @GetMapping("/{id}")
    public Map<String, Object> byId(@PathVariable int id) {
        return Map.of("id", id, "name", "Customer-" + id, "service", "customer-service");
    }
}

package com.digitalnurture.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
public class BillingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
}

@RestController
@RequestMapping("/billing")
class BillingController {

    @GetMapping("/{customerId}")
    public Map<String, Object> invoice(@PathVariable int customerId) {
        return Map.of(
                "customerId", customerId,
                "amountDue", 199.99,
                "currency", "INR",
                "service", "billing-service");
    }
}

package com.cognizant.greet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GreetServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GreetServiceApplication.class, args);
    }
}

@RestController
class GreetController {

    @GetMapping("/greet")
    public String greet() {
        return "Hello World";
    }
}

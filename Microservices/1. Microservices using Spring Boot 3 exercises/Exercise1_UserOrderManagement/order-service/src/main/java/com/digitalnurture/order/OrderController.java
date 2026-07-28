package com.digitalnurture.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repository;
    private final UserClient userClient;

    public OrderController(OrderRepository repository, UserClient userClient) {
        this.repository = repository;
        this.userClient = userClient;
    }

    @GetMapping
    public List<Order> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> byId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates an order, but first calls the User Service to ensure the user
     * exists. If the user is unknown, returns 400.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Order order) {
        UserDto user = userClient.getUser(order.getUserId());
        if (user == null) {
            return ResponseEntity.badRequest()
                    .body("Cannot create order: user " + order.getUserId() + " does not exist");
        }
        Order saved = repository.save(order);
        return ResponseEntity.ok(saved);
    }
}

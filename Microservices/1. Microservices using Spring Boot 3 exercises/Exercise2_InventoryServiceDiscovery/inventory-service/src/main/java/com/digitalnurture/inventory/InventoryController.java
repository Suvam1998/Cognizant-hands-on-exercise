package com.digitalnurture.inventory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryRepository repository;

    public InventoryController(InventoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Inventory> all() {
        return repository.findAll();
    }

    /** Stock level for a product. */
    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> byProduct(@PathVariable Long productId) {
        return repository.findByProductId(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Whether a product has at least the requested quantity in stock. */
    @GetMapping("/{productId}/available")
    public Map<String, Object> available(@PathVariable Long productId,
                                         @org.springframework.web.bind.annotation.RequestParam int quantity) {
        int available = repository.findByProductId(productId)
                .map(Inventory::getQuantityAvailable)
                .orElse(0);
        return Map.of("productId", productId,
                "requested", quantity,
                "inStock", available,
                "available", available >= quantity);
    }

    @PostMapping
    public Inventory upsert(@RequestBody Inventory inventory) {
        return repository.save(inventory);
    }
}

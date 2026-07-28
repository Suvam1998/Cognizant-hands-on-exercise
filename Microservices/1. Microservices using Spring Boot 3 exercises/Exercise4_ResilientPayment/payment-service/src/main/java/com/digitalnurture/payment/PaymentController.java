package com.digitalnurture.payment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Map<String, String> pay(@RequestParam String orderId,
                                   @RequestParam double amount) {
        String result = paymentService.processPayment(orderId, amount);
        return Map.of("result", result);
    }
}

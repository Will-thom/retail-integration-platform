package com.example.erp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/erp-sap")
public class ErpSapController {

    // Exemplo futuro: Payment Service URL configurável
    private final String paymentServiceUrl;

    public ErpSapController(@Value("${payment.service.url:}") String paymentServiceUrl) {
        this.paymentServiceUrl = paymentServiceUrl; // pode ser vazio se não usado
    }

    // 🔹 Health check
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("ERP Adapter (SAP) is running 🚀");
    }

    // 🔹 Simular envio de pedido para o SAP
    @PostMapping("/orders")
    public ResponseEntity<?> sendOrderToSap(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("sapOrderId", UUID.randomUUID().toString());
        response.put("status", "RECEIVED_BY_ERP");
        response.put("received", payload);
        response.put("processedAt", Instant.now());
        return ResponseEntity.ok(response);
    }

    // 🔹 Simular consulta de pedido no SAP
    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrderFromSap(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        response.put("sapOrderId", id);
        response.put("status", "PROCESSED");
        response.put("message", "Order processed successfully in SAP");
        response.put("retrievedAt", Instant.now());
        return ResponseEntity.ok(response);
    }
}
package com.example.orderservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/orders")
public class OrderServiceController {

    private final Map<String, Map<String, Object>> orders = new ConcurrentHashMap<>();

    // 🔹 Health check
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Order Service is running 🚀");
    }

    // 🔹 Criar pedido
    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Map<String, Object> payload) {
        String orderId = UUID.randomUUID().toString();
        payload.put("orderId", orderId);
        payload.put("createdAt", Instant.now().toString());

        orders.put(orderId, payload);

        // Simula envio para ERP (pode ser ajustado para chamar ERP Adapter)
        Map<String, Object> erpResponse = Map.of(
                "sapOrderId", UUID.randomUUID().toString(),
                "status", "RECEIVED_BY_ERP",
                "received", payload,
                "processedAt", Instant.now().toString()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("order", payload);
        response.put("erpResponse", erpResponse);

        return ResponseEntity.ok(response);
    }

    // 🔹 Buscar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") String id) {
        Map<String, Object> order = orders.get(id);
        if (order == null) {
            return ResponseEntity.status(404).body(Map.of(
                    "error", "Order not found",
                    "orderId", id
            ));
        }
        return ResponseEntity.ok(Map.of(
                "orderId", id,
                "status", "LOCAL_ORDER_RETRIEVED",
                "order", order,
                "timestamp", Instant.now().toString()
        ));
    }

    // 🔹 Buscar todos os pedidos
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllOrders() {
        return ResponseEntity.ok(new ArrayList<>(orders.values()));
    }
}
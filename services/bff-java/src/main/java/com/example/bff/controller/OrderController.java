package com.example.bff.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String orderServiceUrl;

    // 🔹 Injeta a URL do order-service do application.properties
    public OrderController(@Value("${order.service.url}") String orderServiceUrl) {
        // Certifique-se de que aponta para o endpoint correto do Order Service
        // Ex: http://order-service:8082/orders
        this.orderServiceUrl = orderServiceUrl;
    }

    // 🔹 Health check simples
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("BFF is running 🚀");
    }

    // 🔹 Buscar pedidos (GET /orders) – agora deve funcionar corretamente
    @GetMapping
    public ResponseEntity<?> getOrders() {
        try {
            System.out.println("Chamando GET em: " + orderServiceUrl);
            // 🔹 Obter todos os pedidos do Order Service
            Object response = restTemplate.getForObject(orderServiceUrl, Object.class);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(Map.of(
                            "error", "Erro ao buscar pedidos do order-service",
                            "message", ex.getMessage()
                    ));
        }
    }

    // 🔹 Criar pedido (orquestração simples)
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> payload) {
        try {
            System.out.println("Chamando POST em: " + orderServiceUrl + " com payload: " + payload);
            Object response = restTemplate.postForObject(orderServiceUrl, payload, Object.class);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(Map.of(
                            "error", "Erro ao criar pedido no order-service",
                            "message", ex.getMessage()
                    ));
        }
    }
}
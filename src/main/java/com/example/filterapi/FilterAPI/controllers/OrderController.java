package com.example.filterapi.FilterAPI.controllers;

import com.example.filterapi.FilterAPI.models.OrderModel;
import com.example.filterapi.FilterAPI.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderModel>> getOrders(
            @RequestParam(required = false) String orderNumber,
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<OrderModel> orderModels = orderService.filterOrders(orderNumber, customerName, status, minAmount, maxAmount, startDate, endDate);
        return ResponseEntity.ok(orderModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable Long id) {
        OrderModel orderModel = orderService.findById(id);
        return orderModel != null ? ResponseEntity.ok(orderModel) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel orderModel) {
        OrderModel createdOrderModel = orderService.saveOrder(orderModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderModel> updateOrder(@PathVariable Long id, @RequestBody OrderModel updatedOrderModel) {
        OrderModel orderModel = orderService.updateOrder(id, updatedOrderModel);
        return orderModel != null ? ResponseEntity.ok(orderModel) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean deleted = orderService.deleteOrder(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

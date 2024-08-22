package com.example.filterapi.FilterAPI.services;

import com.example.filterapi.FilterAPI.models.OrderModel;
import com.example.filterapi.FilterAPI.repositories.OrderRepository;
import com.example.filterapi.FilterAPI.specifications.OrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderModel> filterOrders(String orderNumber, String customerName, String status, Double minAmount, Double maxAmount, LocalDate startDate, LocalDate endDate) {
        return orderRepository.findAll(OrderSpecification.filterOrders(orderNumber, customerName, status, minAmount, maxAmount, startDate, endDate));
    }

    public OrderModel findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderModel saveOrder(OrderModel orderModel) {
        return orderRepository.save(orderModel);
    }

    public OrderModel updateOrder(Long id, OrderModel updatedOrderModel) {
        return orderRepository.findById(id).map(existingOrderModel -> {
            existingOrderModel.setOrderNumber(updatedOrderModel.getOrderNumber());
            existingOrderModel.setCustomerName(updatedOrderModel.getCustomerName());
            existingOrderModel.setStatus(updatedOrderModel.getStatus());
            existingOrderModel.setTotalAmount(updatedOrderModel.getTotalAmount());
            existingOrderModel.setOrderDate(updatedOrderModel.getOrderDate());
            return orderRepository.save(existingOrderModel);
        }).orElse(null);
    }

    public boolean deleteOrder(Long id) {
        return orderRepository.findById(id).map(orderModel -> {
            orderRepository.delete(orderModel);
            return true;
        }).orElse(false);
    }
}

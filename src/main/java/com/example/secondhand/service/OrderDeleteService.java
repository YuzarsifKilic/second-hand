package com.example.secondhand.service;

import com.example.secondhand.model.Order;
import com.example.secondhand.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDeleteService {

    private final OrderRepository repository;

    public OrderDeleteService(OrderRepository repository) {
        this.repository = repository;
    }

    protected void deleteOrdersByUserId(String id) {
        final List<Order> orderList = repository.findAll()
                .stream()
                .filter(o -> o.getCustomer().getId().equals(id))
                .collect(Collectors.toList());

        for (Order orders: orderList) {
            repository.deleteById(orders.getId());
        }
    }
}

package ru.vitstep.sushi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitstep.sushi.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
@Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}

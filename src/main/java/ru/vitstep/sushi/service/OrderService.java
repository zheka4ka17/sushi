package ru.vitstep.sushi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vitstep.sushi.model.Order;
import ru.vitstep.sushi.repository.OrderRepository;
import ru.vitstep.sushi.repository.ProductRepository;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
@Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
    this.productRepository = productRepository;
}

@Transactional
    public void save(Order order){
    orderRepository.save(order);
    }
//@Transactional
//    public void removeProduct(Order order, Long productId){
//    order.getProducts().remove(productRepository.findById(productId).get());
//    }


    public List<Order> findAll(){
    return orderRepository.findAll();
    }

    public Order findById(Long id){
    return orderRepository.findById(id).orElse(null);
    }
}

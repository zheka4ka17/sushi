package ru.vitstep.sushi.service;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vitstep.sushi.model.Order;
import ru.vitstep.sushi.repository.OrderRepository;
import ru.vitstep.sushi.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

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
    try {
        return orderRepository.findById(id).orElse(null);
    }
    catch (Exception e){
        throw new NoSuchElementException("we dont have order with this id");
    }

    }
}

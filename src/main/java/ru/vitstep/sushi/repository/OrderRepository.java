package ru.vitstep.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vitstep.sushi.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

package ru.vitstep.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vitstep.sushi.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
 public List<Product> findProductByTitleContainsIgnoreCase(String findTitle);
}

package ru.vitstep.sushi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vitstep.sushi.model.Product;
import ru.vitstep.sushi.model.Type;
import ru.vitstep.sushi.repository.ProductRepository;
import ru.vitstep.sushi.repository.TypeRepository;

import java.util.List;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final TypeRepository typeRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, TypeRepository typeRepository) {
        this.productRepository = productRepository;
        this.typeRepository = typeRepository;
    }
@Transactional
    public List<Product> findAll(){
    return productRepository.findAll();
    }
@Transactional
    public Product findById(Long id){
    return productRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Product product){
    productRepository.save(product);
}
    @Transactional
    public void update(Long id, Product updated){
    updated.setId(id);
    productRepository.save(updated);

    }

    @Transactional
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
@Transactional
    public List<Product> findByType(Type type){
        return productRepository.findAll().stream().filter(product -> product.getType().equals(type)).collect(Collectors.toList());
    }
@Transactional
    public List<Product> findByTitle(String findTitle){
        return productRepository.findProductByTitleContainsIgnoreCase(findTitle);
    }

    @Transactional
    public void delete(Long id){
        productRepository.delete(productRepository.findById(id).orElse(null));
    }





}

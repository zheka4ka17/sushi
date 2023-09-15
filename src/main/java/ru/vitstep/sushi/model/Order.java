package ru.vitstep.sushi.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.access.method.P;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull
    private User user;

    private BigDecimal fullPrice;
    // @NotEmpty(message = "Address should be not empty")
    private String address;

    public Order() {
        this.fullPrice= BigDecimal.valueOf(0);
    }

    private String info;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_products",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();




    public void addProduct(Product product){
        products.add(product);
        BigDecimal price = new BigDecimal(0);
//        for (Product prod : products)
//            price=price.add(prod.getPrice());
        this.fullPrice=this.fullPrice.add(product.getPrice());
    }

    public void removeProduct(Product product){
        this.fullPrice=fullPrice.subtract(product.getPrice());
        products.remove(product);

    }

    }



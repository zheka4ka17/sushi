package ru.vitstep.sushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.vitstep.sushi.model.Order;
import ru.vitstep.sushi.model.Product;
import ru.vitstep.sushi.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@SessionAttributes("order")
public class MainController {
    private final ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String index(Model model) {
        List<Product> randomList= new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<8; i++){
            int rand= random.nextInt(productService.findAll().size());
            randomList.add(productService.findAll().get((rand)));
        }
        model.addAttribute("randList",randomList);
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }
    @GetMapping("/addProduct")
    private String addProductToOrder(Product product, Model model) {
        Order order=(Order) model.getAttribute("order");
        order.addProduct(product);
        return "redirect:/";
    }

}


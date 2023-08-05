package ru.vitstep.sushi.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vitstep.sushi.model.service.ProductService;

@Controller
public class MainController {
    private final ProductService productService;
@Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String index(){
    return "index";



    }
}

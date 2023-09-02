package ru.vitstep.sushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vitstep.sushi.model.Order;
import ru.vitstep.sushi.model.Product;
import ru.vitstep.sushi.security.UserDetail;
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

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        } else {
            model.addAttribute("auth", true);
            UserDetail userDetail = (UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user", userDetail.getUser());
        }
        List<Product> randomList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(productService.findAll().size());
            randomList.add(productService.findAll().get((rand)));
        }
        model.addAttribute("randList", randomList);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @GetMapping("/addProduct/{id}")
    private String addProductToOrder(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        Order order = (Order) model.getAttribute("order");
        order.addProduct(product);
        return "redirect:/";
    }

    @GetMapping("/kioto")
    public String kioto(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        }
        else {
            model.addAttribute("auth", true);
            UserDetail userDetail=(UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user",userDetail.getUser());
        }
        return "kioto";
    }

    @GetMapping("/delivery_info")
    public String deliverInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        } else {
            model.addAttribute("auth", true);
            UserDetail userDetail = (UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user", userDetail.getUser());
        }
        return "delivery_info";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        } else {
            model.addAttribute("auth", true);
            UserDetail userDetail = (UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user", userDetail.getUser());
        }
        return "contacts";
    }

    @GetMapping("/policy")
    public String policy(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        } else {
            model.addAttribute("auth", true);
            UserDetail userDetail = (UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user", userDetail.getUser());
        }
        return "policy";


    }
}


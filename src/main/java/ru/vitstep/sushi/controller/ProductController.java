package ru.vitstep.sushi.controller;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vitstep.sushi.model.Order;
import ru.vitstep.sushi.model.Product;
import ru.vitstep.sushi.model.User;
import ru.vitstep.sushi.security.UserDetail;
import ru.vitstep.sushi.service.ProductService;
import ru.vitstep.sushi.service.TypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/menu")
@SessionAttributes("order")
public class ProductController {

    private final ProductService productService;
    private final TypeService typeService;

    @Autowired
    public ProductController(ProductService productService, TypeService typeService) {
        this.productService = productService;
        this.typeService = typeService;
    }


    @GetMapping("/sushi")
    public String getSushi(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        }
        else {
            model.addAttribute("auth", true);
            UserDetail userDetail=(UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user",userDetail.getUser());
        }

        List<Product> sushi = productService.findAll().stream().filter(product -> product.getType().getId() == 1).collect(Collectors.toList());
        model.addAttribute("sushis", sushi);
        return "menu/sushi";
    }

    @GetMapping("/roll")
    public String getRolls(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        }
        else {
            model.addAttribute("auth", true);
            UserDetail userDetail=(UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user",userDetail.getUser());
        }
        List<Product> rolls = productService.findAll().stream().filter(product -> product.getType().getId() == 2).collect(Collectors.toList());
        model.addAttribute("rolls", rolls);
        return "menu/roll";
    }


    @GetMapping("/sets")
    public String getSets(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        }
        else {
            model.addAttribute("auth", true);
            UserDetail userDetail=(UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user",userDetail.getUser());
        }
        List<Product> sets = productService.findAll().stream().filter(product -> product.getType().getId() == 3).collect(Collectors.toList());
        model.addAttribute("sets", sets);
        return "menu/set";
    }

    @GetMapping("/hot-rolls")
    public String getHotRolls(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        }
        else {
            model.addAttribute("auth", true);
            UserDetail userDetail=(UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user",userDetail.getUser());
        }
        List<Product> hotRolls = productService.findAll().stream().filter(product -> product.getType().getId() == 4).collect(Collectors.toList());
        model.addAttribute("hot_rolls", hotRolls);
        return "menu/hot_roll";
    }

    @GetMapping("/dessert")
    public String getDesserts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        }
        else {
            model.addAttribute("auth", true);
            UserDetail userDetail=(UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user",userDetail.getUser());
        }
        List<Product> desserts = productService.findAll().stream().filter(product -> product.getType().getId() == 5).collect(Collectors.toList());
        model.addAttribute("desserts", desserts);
        return "menu/dessert";
    }

    @GetMapping("/drinks")
    public String getSDrinks(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        }
        else {
            model.addAttribute("auth", true);
            UserDetail userDetail=(UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user",userDetail.getUser());
        }
        List<Product> drinks = productService.findAll().stream().filter(product -> product.getType().getId() == 6).collect(Collectors.toList());
        model.addAttribute("drinks", drinks);
        return "menu/drink";
    }

    @GetMapping("/salad")
    public String getSalads(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        }
        else {
            model.addAttribute("auth", true);
            UserDetail userDetail=(UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user",userDetail.getUser());
        }
        List<Product> salads = productService.findAll().stream().filter(product -> product.getType().getId() == 7).collect(Collectors.toList());
        model.addAttribute("salads", salads);
        return "menu/salad";
    }


    @GetMapping("/sauce")
    public String getSauces(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication.getPrincipal() instanceof String)) {
            model.addAttribute("not_auth", true);

        }
        else {
            model.addAttribute("auth", true);
            UserDetail userDetail=(UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user",userDetail.getUser());
        }
        List<Product> sauces = productService.findAll().stream().filter(product -> product.getType().getId() == 8).collect(Collectors.toList());
        model.addAttribute("sauces", sauces);

        return "menu/sauce";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        if (authentication.getPrincipal() instanceof UserDetail) {
          UserDetail userDetail = (UserDetail) authentication.getPrincipal();
          User user = userDetail.getUser();
            System.out.println(user.getRole().getId());
        if(user.getRole().getId()==2)

         model.addAttribute("admin", user);}



        if (authentication.getPrincipal() instanceof String) {
            model.addAttribute("not_auth", true);
        }
        else {
            model.addAttribute("auth", true);
            UserDetail userDetail=(UserDetail) authentication.getPrincipal();
            model.addAttribute("auth_user",userDetail.getUser());
        }

        List<Product> random1= new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<5; i++){
            int rand= random.nextInt(productService.findAll().size());
            random1.add(productService.findAll().get((rand)));
        }
        model.addAttribute("randList",random1);
        return "menu/show";

    }

    @GetMapping("/add-product/{id}")
    public String addProductToOrder(@PathVariable("id") Long id, Model model) {
        Order order = (Order) model.getAttribute("order");
        Product product = productService.findById(id);
        order.addProduct(product);
        if (product.getType().getTitle().equals("Суши"))
            return "redirect:/menu/sushi";
        else if (product.getType().getTitle().equals("Роллы"))
            return "redirect:/menu/roll";
        else if (product.getType().getTitle().equals("Наборы"))
            return "redirect:/menu/sets";
        else if (product.getType().getTitle().equals("Горячие роллы"))
            return "redirect:/menu/hot-rolls";
        else if (product.getType().getTitle().equals("Десерты"))
            return "redirect:/menu/dessert";
        else if (product.getType().getTitle().equals("Напитки"))
            return "redirect:/menu/drinks";
        else if (product.getType().getTitle().equals("Coycы"))
            return "redirect:/menu/sauce";
        else return "redirect:/menu/salad";
        }



    }



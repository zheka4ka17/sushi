package ru.vitstep.sushi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vitstep.sushi.model.Product;
import ru.vitstep.sushi.model.Type;
import ru.vitstep.sushi.service.OrderService;
import ru.vitstep.sushi.service.ProductService;
import ru.vitstep.sushi.service.TypeService;
import ru.vitstep.sushi.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final TypeService typeService;

    public AdminController(ProductService productService, OrderService orderService, UserService userService, TypeService typeService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
        this.typeService = typeService;
    }

    @GetMapping("/product")
    public String menu(Model model, @RequestParam(name = "findTitle",required = false) String findTitle) {
        if (!(findTitle ==null) && !findTitle.isBlank())
        {
            model.addAttribute("products", productService.findByTitle(findTitle));
        }
        else{
            List<Type> types = typeService.findAll();
            for (Type type : types)
                model.addAttribute(type.getTitle(), productService.findByType(type));
        }
        return "menu/menu";
    }

    @GetMapping("/new-product")
    public String newProduct(Model model){
        model.addAttribute("newProduct", new Product());
        model.addAttribute("types",typeService.findAll());
        return "menu/new_product";
    }

    @PostMapping("/new-product")
    public String create(@ModelAttribute("newProduct") Product product){
        productService.save(product);
        return "redirect:/menu";
    }
    @GetMapping("/types")
    public String showAll(Model model){
        model.addAttribute("types", typeService.findAll());
        return "type/types";
    }

    @GetMapping("/new-type")
    public String createType(Model model){
        model.addAttribute("type", new Type());
        return "type/new_type";
    }

    @PostMapping("/new-type")
    public String create(@ModelAttribute("type") Type type){
        typeService.addType(type);
        return "redirect:/type";
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/all";
    }
    @GetMapping("user/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        model.addAttribute("user",userService.findById(id));
        return "user/show_user";
    }


    @GetMapping("user/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "redirect:/user/all";
    }
}





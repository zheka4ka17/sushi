package ru.vitstep.sushi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vitstep.sushi.model.Product;
import ru.vitstep.sushi.model.Type;
import ru.vitstep.sushi.model.User;
import ru.vitstep.sushi.security.UserDetail;
import ru.vitstep.sushi.service.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final TypeService typeService;
    private final RoleService roleService;

    public AdminController(ProductService productService, OrderService orderService, UserService userService, TypeService typeService, RoleService roleService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
        this.typeService = typeService;
        this.roleService = roleService;
    }


    @GetMapping
    public String adminIndex(Model model){
        model.addAttribute("users", userService.findAll());
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail= (UserDetail) authentication.getPrincipal();
        model.addAttribute("user",userDetail.getUser());
        return "admin_users";
    }

//    @GetMapping("/product")
//    public String menu(Model model, @RequestParam(name = "findTitle",required = false) String findTitle) {
//        if (!(findTitle ==null) && !findTitle.isBlank())
//        {
//            model.addAttribute("products", productService.findByTitle(findTitle));
//        }
//        else{
//            List<Type> types = typeService.findAll();
//            for (Type type : types)
//                model.addAttribute(type.getTitle(), productService.findByType(type));
//        }
//        return "menu/menu";
//    }
    @GetMapping("/product")
    public String allProducts(Model model){
        model.addAttribute("products", productService.findAll());
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail= (UserDetail) authentication.getPrincipal();
        model.addAttribute("user",userDetail.getUser());
        return "admin_products";
    }

//    @GetMapping("/new-product")
//    public String newProduct(Model model){
//        model.addAttribute("newProduct", new Product());
//        model.addAttribute("types",typeService.findAll());
//        return "menu/new_product";
//    }
//
//    @PostMapping("/new-product")
//    public String create(@ModelAttribute("newProduct") Product product){
//        System.out.println(product);
//        productService.save(product);
//        return "redirect:/admin/product";
//    }

    @GetMapping("product/{id}/edit")
    public String editProduct(Model model, @PathVariable("id") Long id) {

        model.addAttribute("product", productService.findById(id));
        model.addAttribute("types",typeService.findAll());
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail= (UserDetail) authentication.getPrincipal();
        model.addAttribute("user",userDetail.getUser());
        return "edit_product";
    }

    @PostMapping("product/{id}/edit")
    public String changeUser(@ModelAttribute("product") Product product,@PathVariable("id") Long id){
        productService.update(id, product);
        return "redirect:/admin/product";

    }
//    @GetMapping("/types")
//    public String showAll(Model model){
//        model.addAttribute("types", typeService.findAll());
//        return "type/types";
//    }
//
//    @GetMapping("/new-type")
//    public String createType(Model model){
//        model.addAttribute("type", new Type());
//        return "type/new_type";
//    }

    @PostMapping("/new-type")
    public String create(@ModelAttribute("type") Type type){
        typeService.addType(type);
        return "redirect:/types";
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail= (UserDetail) authentication.getPrincipal();
        model.addAttribute("user",userDetail.getUser());

        return "all_order";
    }

        @GetMapping("order/{id}/show")
        public String showOrder(@PathVariable("id") Long id, Model model){
        model.addAttribute("order", orderService.findById(id));
        return "show_order";

    }


    @GetMapping("user/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("admin", true);
        return "edit_user";
    }

    @PostMapping("user/{id}/edit")
    public String changeUser(@ModelAttribute("user") User user,@PathVariable("id") Long id){
        userService.update(id, user);
        return "redirect:/admin/users";

    }


    @GetMapping("/product/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id){
        System.out.println(productService.findById(id));
        productService.delete(id);
        return "redirect:/admin/product";
    }
}





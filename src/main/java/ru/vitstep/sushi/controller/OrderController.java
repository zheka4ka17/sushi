package ru.vitstep.sushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vitstep.sushi.model.Order;
import ru.vitstep.sushi.model.User;
import ru.vitstep.sushi.security.UserDetail;
import ru.vitstep.sushi.service.OrderService;
import ru.vitstep.sushi.service.ProductService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("order")
@SessionAttributes("order")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService, ProductService productService1) {
        this.orderService = orderService;

        this.productService = productService1;
    }




    @GetMapping("/current")
    public String getCurrent(Model model ){
        Order order=(Order)model.getAttribute("order");
        //System.out.println(order+ "veVADFBSFG");
        if (order == null || order.getProducts().isEmpty()) {
            return "redirect:/";
        }
        Order newOrder= new Order();
        model.addAttribute("newOrder",newOrder );


        return "order/current";
    }

    @PostMapping("/current")
    public String doOrder(@ModelAttribute("newOrder") Order newOrder,
                          @ModelAttribute("order") Order order){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail= (UserDetail)authentication.getPrincipal();
        User user=userDetail.getUser();
        newOrder.setUser(user);
        newOrder.setProducts(order.getProducts());
        newOrder.setCreated(LocalDateTime.now());
        newOrder.setFullPrice(order.getFullPrice());
       // newOrder.setUser(user);
      //  newOrder.setFullPrice(order.getFullPrice());
//        Order order= (Order) model.getAttribute("order");
//        order.setUser(user);
//        order.setCreated(LocalDateTime.now());
      //  System.out.println(newOrder);
        orderService.save(newOrder);
        return "order/finish-order";
    }

    @GetMapping("/deleteFromOrder/{id}")
    public String deleteFromOrder(@PathVariable("id") Long id, Model model){
        Order order= (Order) model.getAttribute("order");
        order.removeProduct(productService.findById(id));

        return "redirect:/order/current";

    }
}

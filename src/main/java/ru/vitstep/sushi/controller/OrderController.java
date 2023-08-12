package ru.vitstep.sushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vitstep.sushi.model.Order;
import ru.vitstep.sushi.service.OrderService;

@Controller
@RequestMapping("order")
@SessionAttributes("order")
public class OrderController {

    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }




    @GetMapping("/current")
    public String getCurrent(@SessionAttribute("order") Order order, Model model){
    model.addAttribute("order", order);

    return "order/current";
    }
}

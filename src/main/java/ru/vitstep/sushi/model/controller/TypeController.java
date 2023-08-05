package ru.vitstep.sushi.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vitstep.sushi.model.Type;
import ru.vitstep.sushi.model.service.TypeService;

@Controller
@RequestMapping("/type")
public class TypeController {
    private final TypeService typeService;
@Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public String showAll(Model model){
    model.addAttribute("types", typeService.findAll());
    return "type/types";
    }

    @GetMapping("/new")
    public String createType(Model model){
    model.addAttribute("type", new Type());
    return "type/new_type";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("type") Type type){
    typeService.addType(type);
    return "redirect:/type";
    }
}

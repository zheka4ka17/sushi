package ru.vitstep.sushi.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vitstep.sushi.model.Role;
import ru.vitstep.sushi.model.User;
import ru.vitstep.sushi.model.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/all";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user/new_user";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {
        user.setRole(Role.valueOf("USER"));
        userService.update(user);
        return "redirect:/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "user/update_form";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") /*@Valid*/ User user, //BindingResult bindingResult,
                         @PathVariable("id") Long id) {
//        if (bindingResult.hasErrors())
//            return "people/edit";
        userService.update(id, user);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        model.addAttribute("user",userService.findById(id));
        return "user/show_user";
    }


    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "redirect:/user/all";
    }
}
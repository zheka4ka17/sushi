package ru.vitstep.sushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vitstep.sushi.model.Role;
import ru.vitstep.sushi.model.User;
import ru.vitstep.sushi.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("order")
public class UserController {

    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/login")
    public String login() {
        return "user/login";
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {
//        user.setRole(Role.valueOf("USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
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
}


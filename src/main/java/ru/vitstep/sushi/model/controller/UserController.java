package ru.vitstep.sushi.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String findAll(Model model){
    model.addAttribute("users", userService.findAll());
    return "user/all";
}

   @GetMapping("/new")
    public String newUser(Model model){
    model.addAttribute("user", new User());
    return "user/new_user";
   }

   @PostMapping("/new")
    public String create(@ModelAttribute("user") User user){
    user.setRole(Role.valueOf("USER"));
    userService.createOrUpdate(user);
    return "redirect:/index";
   }
}

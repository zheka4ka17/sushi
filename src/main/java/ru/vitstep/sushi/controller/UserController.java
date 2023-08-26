package ru.vitstep.sushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vitstep.sushi.model.Role;
import ru.vitstep.sushi.model.User;
import ru.vitstep.sushi.security.UserDetail;
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

    @GetMapping("/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "user/user_update";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") /*@Valid*/ User user, //BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/";
    }

    @GetMapping("/myRoom")
    public String myRoom(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail= (UserDetail)authentication.getPrincipal();
        User user=userDetail.getUser();
        model.addAttribute("user", user);
        return "user/show_user";
    }
}


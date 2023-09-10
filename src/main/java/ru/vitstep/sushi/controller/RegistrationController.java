package ru.vitstep.sushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vitstep.sushi.model.User;
import ru.vitstep.sushi.service.RoleService;
import ru.vitstep.sushi.service.UserService;
import ru.vitstep.sushi.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@SessionAttributes("order")
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    private final UserValidator userValidator;

    @Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;

        this.userValidator = userValidator;
    }

    @GetMapping
    public String registerForm(Model model) {


        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors())
            return "registration";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.findByName("ROLE_USER"));
        userService.save(user);
        return "redirect:/login";
    }
}
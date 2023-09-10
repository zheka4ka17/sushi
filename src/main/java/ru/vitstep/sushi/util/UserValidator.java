package ru.vitstep.sushi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vitstep.sushi.model.User;
import ru.vitstep.sushi.service.UserService;

@Component
public class UserValidator implements Validator {
    private final UserService userService;
@Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user=(User) target;
        if(userService.findByEmail(user.getEmail())!=null)
            errors.rejectValue("email","","this email is already taken");
        else if (userService.findByUsername(user.getUsername())!=null)
            errors.rejectValue("username","","this username is already taken");


    }
}

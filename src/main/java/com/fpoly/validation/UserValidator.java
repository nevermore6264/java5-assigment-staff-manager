package com.fpoly.validation;

import com.fpoly.model.User;
import com.fpoly.service.UserService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator{

    private UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
        String userName = user.getUserName();
        String fullName = user.getFullName();
        String password = user.getPassWord();

        ValidationUtils.rejectIfEmpty(errors, "userName", "userName.empty");
        ValidationUtils.rejectIfEmpty(errors, "fullName", "fullName.empty");
        ValidationUtils.rejectIfEmpty(errors, "passWord", "password.empty");

        if(userService.existUsername(userName)) {
            errors.rejectValue("userName", "userName.exists" );
        }

    }
}

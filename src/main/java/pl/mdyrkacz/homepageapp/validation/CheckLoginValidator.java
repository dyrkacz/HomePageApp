package pl.mdyrkacz.homepageapp.validation;

import pl.mdyrkacz.homepageapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckLoginValidator implements ConstraintValidator<CheckLogin, String> {


    UserService userService;

    public CheckLoginValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(CheckLogin constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && !userService.existByUsername(username);
    }
}
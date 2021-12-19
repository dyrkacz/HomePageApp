package pl.mdyrkacz.homepageapp;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.mdyrkacz.homepageapp.user.User;
import pl.mdyrkacz.homepageapp.user.UserServiceImpl;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserServiceImpl userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/user/tweets";
    }

    @GetMapping(value = {"/login"})
    public String login() {
        return "login";
    }


    @GetMapping(value = {"/register"})
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = {"/register"})
    public String register(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        } else {
            userService.saveUser(user);
            return "redirect:/user";
        }
    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        userService.saveUser(user);
        return "admin";
    }
}

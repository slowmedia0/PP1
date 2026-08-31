package com.slowmedia0.PP1.web;

import com.slowmedia0.PP1.database.UserRepository;
import com.slowmedia0.PP1.entity.User;
import com.slowmedia0.PP1.services.UserService;
import com.slowmedia0.PP1.tools.RegistrationForm;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(name = "goToRegister")
    public String goToRegister(){
        return "register";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form){
        userService.defaultProcessUser(form,passwordEncoder);
        return "redirect:/home";
    }
}

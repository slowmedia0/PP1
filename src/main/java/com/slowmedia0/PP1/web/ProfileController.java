package com.slowmedia0.PP1.web;

import com.slowmedia0.PP1.DTO.UserDTO;
import com.slowmedia0.PP1.DTO.UserMapper;
import com.slowmedia0.PP1.entity.User;
import com.slowmedia0.PP1.entity.Wallet;
import com.slowmedia0.PP1.services.UserService;
import com.slowmedia0.PP1.services.WalletService;
import jdk.jshell.spi.ExecutionControl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("userProfileController")
@Data
@RequestMapping("/profile")
public class ProfileController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final WalletService walletService;


    @Autowired
    public ProfileController(UserMapper userMapper, UserService userService, WalletService walletService) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.walletService = walletService;
    }

    @GetMapping
    public String goToProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        UserDTO userDTO = userMapper.toDto(user);
        model.addAttribute("userDTO", userDTO);
        return "profile";
    }

}

package com.slowmedia0.PP1.services;

import com.slowmedia0.PP1.database.CartRepository;
import com.slowmedia0.PP1.database.UserRepository;
import com.slowmedia0.PP1.database.WalletRepository;
import com.slowmedia0.PP1.entity.Cart;
import com.slowmedia0.PP1.entity.User;
import com.slowmedia0.PP1.entity.Wallet;
import com.slowmedia0.PP1.tools.RegistrationForm;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService {
    private  final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User defaultProcessUser(RegistrationForm registrationForm, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setLogin(registrationForm.login());
        user.setMail(registrationForm.mail());
        user.setPassword(passwordEncoder.encode(registrationForm.password()));
        user.setPhoneNumber(registrationForm.phoneNumber());
        user.setOrders(new ArrayList<>());

        user = userRepository.save(user);

        return user;
    }

    @Transactional(readOnly = true)
    public User getCurrentUserWithCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return userRepository.findByLoginWithCart(user.getLogin()).get();
    }

    @Transactional(readOnly = true)
    public User getCurrentUserWithWallet(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return userRepository.findByLoginWithWallet(user.getLogin()).get();
    }
}

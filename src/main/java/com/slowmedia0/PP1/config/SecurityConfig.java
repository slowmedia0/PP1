package com.slowmedia0.PP1.config;

import com.slowmedia0.PP1.database.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return login -> userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Пользователь с логином '" + login + "' не найден!"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests(auth -> auth.
                        requestMatchers("/cart/order","/cart","/favorite", "/profile").
                        hasRole("USER").requestMatchers("/","/**")
                        .permitAll()
                ).formLogin(form -> form.loginPage("/login")
                                .usernameParameter("login")
                        .defaultSuccessUrl("/home",true)
                ).logout(logout -> logout.logoutSuccessUrl("/login"))
                .build();
    }
}

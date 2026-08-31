package com.slowmedia0.PP1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile/favorite")
public class FavoriteController {
    @GetMapping
    public String favorite(){
        return "favorite";
    }

}

package com.slowmedia0.PP1.web;

import com.slowmedia0.PP1.DTO.WalletDTO;
import com.slowmedia0.PP1.DTO.WalletMapper;
import com.slowmedia0.PP1.entity.WalletHistory;
import com.slowmedia0.PP1.services.UserService;
import com.slowmedia0.PP1.services.WalletService;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@Controller
@RequestMapping("/profile/wallet")
public class WalletController {
    private final WalletService walletService;
    private final UserService userService;
    private final WalletMapper walletMapper;

    @Autowired
    public WalletController(WalletService walletService, UserService userService, WalletMapper walletMapper) {
        this.walletService = walletService;
        this.userService = userService;
        this.walletMapper = walletMapper;
    }

    @GetMapping
    public String goToWallet(Model model){
        WalletHistory walletHistory = walletService.getCurrentWalletHistory();
        WalletDTO walletDTO = walletMapper.toDto(walletHistory.getWallet());
        model.addAttribute("walletDTO", walletDTO);
        model.addAttribute("balanceInRubles", WalletService.getPriceInRubles(walletDTO.balance()));
        return "wallet";
    }

    @PostMapping(params = "depositWallet")
    public String depositWallet(@RequestParam("sumOfDeposit") BigInteger sumOfDeposit){
        walletService.depositWallet(sumOfDeposit);
        return "redirect:/profile/wallet";
    }

}
package com.slowmedia0.PP1.services;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.slowmedia0.PP1.database.WalletHistoryRepository;
import com.slowmedia0.PP1.database.WalletRepository;
import com.slowmedia0.PP1.entity.*;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletHistoryRepository walletHistoryRepository;
    private final UserService userService;

    public WalletService(WalletRepository walletRepository, WalletHistoryRepository walletHistoryRepository, UserService userService) {
        this.walletRepository = walletRepository;
        this.walletHistoryRepository = walletHistoryRepository;
        this.userService = userService;
    }

    public Wallet defaultProcessWallet(User user){
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(BigInteger.ZERO);
        return walletRepository.save(wallet);
    }

    private WalletHistory defaultProcessWalletHistory(Wallet wallet){
        WalletHistory walletHistory = new WalletHistory();
        wallet.setWalletHistory(walletHistory);
        walletHistory.setWallet(wallet);
        return walletHistoryRepository.save(walletHistory);
    }

    @Transactional
    public Wallet processAndGetWalletByUser(User user) {
        Wallet wallet = defaultProcessWallet(user);
        return walletRepository.save(wallet);
    }

    @Transactional
    public WalletHistory processAndGetWalletHistoryByWallet(Wallet wallet) {
        WalletHistory walletHistory = defaultProcessWalletHistory(wallet);
        return walletHistoryRepository.save(walletHistory);
    }

    @Transactional
    public void depositWallet(BigInteger sumOfDeposit){
        Wallet wallet = getCurrentWallet();
        WalletHistory walletHistory = processAndGetWalletHistoryByWallet(wallet);

        walletHistory.setWalletTransactionType(WalletTransactionType.DEPOSIT);
        walletHistory.setWalletTransactionStatus(WalletTransactionStatus.PENDING);
        walletHistory.setTimeOfOperation(LocalDateTime.now());
        walletHistory.setSumPriceOfOperation(sumOfDeposit);
        walletHistoryRepository.save(walletHistory);

        if (validateDeposit(sumOfDeposit)) {

            wallet.setBalance(wallet.getBalance().add(sumOfDeposit));

            walletHistory.setWalletTransactionStatus(WalletTransactionStatus.COMPLETED);
            walletHistory.setTimeOfOperation(LocalDateTime.now());
        }
        else {
            walletHistory.setWalletTransactionStatus(WalletTransactionStatus.FAILED);
            walletHistory.setTimeOfOperation(LocalDateTime.now());
        }
    }

    @Transactional
    public void payWallet(BigInteger sumOfPayment){
        Wallet wallet = getCurrentWallet();
        WalletHistory walletHistory = processAndGetWalletHistoryByWallet(wallet);

        walletHistory.setWalletTransactionType(WalletTransactionType.PAYMENT);
        walletHistory.setWalletTransactionStatus(WalletTransactionStatus.PENDING);
        walletHistory.setTimeOfOperation(LocalDateTime.now());
        walletHistory.setSumPriceOfOperation(sumOfPayment);
        walletHistoryRepository.save(walletHistory);

        if (validatePayment(sumOfPayment)) {

            wallet.setBalance(wallet.getBalance().subtract(sumOfPayment));

            walletHistory.setWalletTransactionStatus(WalletTransactionStatus.COMPLETED);
            walletHistory.setTimeOfOperation(LocalDateTime.now());
        }
        else {
            walletHistory.setWalletTransactionStatus(WalletTransactionStatus.FAILED);
            walletHistory.setTimeOfOperation(LocalDateTime.now());
        }
    }

    @Transactional
    public void refundWallet(BigInteger sumOfRefund){
        Wallet wallet = getCurrentWallet();
        WalletHistory walletHistory = processAndGetWalletHistoryByWallet(wallet);

        walletHistory.setWalletTransactionType(WalletTransactionType.REFUND);
        walletHistory.setWalletTransactionStatus(WalletTransactionStatus.PENDING);
        walletHistory.setTimeOfOperation(LocalDateTime.now());
        walletHistory.setSumPriceOfOperation(sumOfRefund);
        walletHistoryRepository.save(walletHistory);

        //Добавить этап валидации, в случае не соответствии валидации операция переходит в статус FAILED и все изменения с БД откатываются
        if (validateRefund(sumOfRefund)) {

            wallet.setBalance(wallet.getBalance().add(sumOfRefund));

            walletHistory.setWalletTransactionStatus(WalletTransactionStatus.COMPLETED);
            walletHistory.setTimeOfOperation(LocalDateTime.now());
        }
        else {
            walletHistory.setWalletTransactionStatus(WalletTransactionStatus.FAILED);
            walletHistory.setTimeOfOperation(LocalDateTime.now());
        }
    }

    private boolean validateDeposit(BigInteger sumOfDeposit){
        return true;
    }

    private boolean validatePayment(BigInteger sumOfPayment){
        Wallet wallet = getCurrentWallet();
        if (wallet.getBalance().subtract(sumOfPayment).compareTo(BigInteger.ZERO)<0) return false;
        return true;
    }

    private boolean validateRefund(BigInteger sumOfRefund){
        return true;
    }

    public Wallet getCurrentWallet(){
        User user = userService.getCurrentUserWithWallet();
        Wallet wallet;
        if (user.getWallet()==null) {
            wallet = processAndGetWalletByUser(user);
        }
        else {
            wallet = user.getWallet();
        }
        return wallet;
    }

    public WalletHistory getCurrentWalletHistory(){
        Wallet wallet = getCurrentWallet();
        WalletHistory walletHistory;
        if (wallet.getWalletHistory()==null){
            walletHistory = processAndGetWalletHistoryByWallet(wallet);
        }
        else {
            walletHistory = wallet.getWalletHistory();
        }
        return walletHistory;
    }

    public static BigDecimal getPriceInRubles(BigInteger priceInKopeks) {
        return new BigDecimal(priceInKopeks).divide(new BigDecimal(100));
    }
}


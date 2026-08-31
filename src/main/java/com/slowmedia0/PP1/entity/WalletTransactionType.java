package com.slowmedia0.PP1.entity;

import lombok.Getter;

@Getter
public enum WalletTransactionType {
    DEPOSIT("Депозит"), PAYMENT("Оплата"), REFUND("Возврат");

    private final  String title;

    WalletTransactionType(String title) {
        this.title = title;
    }
}

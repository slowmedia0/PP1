package com.slowmedia0.PP1.entity;

import lombok.Getter;

@Getter
public enum WalletTransactionStatus {
    PENDING("В обработке"), COMPLETED("Выполнено успешно"), FAILED("Не удалось выполнить"), CANCELLED("Отменено");

    private final String title;

    WalletTransactionStatus(String title) {
        this.title = title;
    }
}

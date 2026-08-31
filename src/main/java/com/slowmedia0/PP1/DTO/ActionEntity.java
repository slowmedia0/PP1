package com.slowmedia0.PP1.DTO;

import lombok.Getter;

@Getter
public enum ActionEntity {
    CREATED("Создано"), UPDATED("Изменено"), DELETED("Удалено"), NO_ACTION("Без изменений");

    private final String title;

    ActionEntity(String title) {
        this.title = title;
    }
}

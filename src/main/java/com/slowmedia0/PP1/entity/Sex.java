package com.slowmedia0.PP1.entity;

import lombok.Getter;

@Getter
public enum Sex {
    MALE("Мужчина"), FEMALE("Женщина");

    private final String title;

    Sex(String title) {
        this.title = title;
    }
}

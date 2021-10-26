package ru.sandreeva.domain;

public enum MenuItem {
    VIDEO("Видео"),
    MAPS("Карты");

    private String desc;

    MenuItem(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
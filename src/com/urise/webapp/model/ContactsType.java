package com.urise.webapp.model;

public enum ContactsType {

    PHONE("Телефон"),
    EMAIL("Email"),
    SKYPE("Skype");

    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + ": ";
    }
}

package com.urise.webapp.model;

import java.util.ArrayList;

public class ListSection extends Section {

    public ArrayList<String> list;

    public ListSection(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String el : list) {
            builder.append(el);
            builder.append("\n");
        }
        return builder.toString();
    }
}

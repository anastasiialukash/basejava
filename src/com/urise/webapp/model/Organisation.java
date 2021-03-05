package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Organisation {

    private final String orgName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Organisation(
            String orgName,
            LocalDate startDate,
            LocalDate endDate,
            String title,
            String description) {

        this.orgName = orgName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        ArrayList<String> list = new ArrayList<>();
        list.add(orgName);
        list.add(startDate.toString());
        list.add(endDate.toString());
        list.add(title);
        list.add(description);

        StringBuilder builder = new StringBuilder();

        for (String el : list) {
            if (el != null) {
                builder.append(el).append("\n");
            }
        }

        builder.append("\n");

        return builder.toString();
    }
}

package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organisation {

    private final Link homePage;
    private final List<ResumeHistory> resumeHistory;


    public Organisation(String name, String url, List<ResumeHistory> resumeHistory) {
        this.homePage = new Link(name, url);
        this.resumeHistory = resumeHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organisation that = (Organisation) o;
        return Objects.equals(homePage, that.homePage) && Objects.equals(resumeHistory, that.resumeHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, resumeHistory);
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "homePage=" + homePage +
                ", resumeHistory=" + resumeHistory +
                '}';
    }
}

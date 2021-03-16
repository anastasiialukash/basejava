package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organisation {

    private final Link homePage;
    private final List<Experience> experience;


    public Organisation(String name, String url, List<Experience> experience) {
        this.homePage = new Link(name, url);
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organisation that = (Organisation) o;
        return Objects.equals(homePage, that.homePage) && Objects.equals(experience, that.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, experience);
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "homePage=" + homePage +
                ", resumeHistory=" + experience +
                '}';
    }
}

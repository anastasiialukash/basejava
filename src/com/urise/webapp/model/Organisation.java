package com.urise.webapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organisation implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Link homePage;
    private List<Experience> experience;

    public Organisation(String name, String url, Experience... experience) {
        this(new Link(name, url), Arrays.asList(experience));
    }

    public Organisation(Link homePage, List<Experience> experience) {
        this.homePage = homePage;
        this.experience = experience;
    }

//    public Organisation(String name, String url, List<Experience> experience) {
//        this.homePage = new Link(name, url);
//        this.experience = experience;
//    }

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
//        return homePage +
//                ", experience:" + experience;
        return "Organization(" + homePage + "," + experience + ')';
    }
}

package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;
import static com.urise.webapp.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organisation implements Serializable {

    private static final long serialVersionUID = 1L;
    private Link homePage;
    private List<Experience> experience;

    public Link getHomePage() {
        return homePage;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public Organisation() {
    }

    public Organisation(String name, String url, Experience... experience) {
        this(new Link(name, url), Arrays.asList(experience));
    }

    public Organisation(Link homePage, List<Experience> experience) {
        this.homePage = homePage;
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
        if (experience != null) {
            return "Organisation(" + homePage + "," + experience + ')';
        }
        return "Organisation(" + homePage;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Experience implements Serializable {

        private static final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public Experience() {
        }

        public Experience(LocalDate startDate, LocalDate endDate, String title, String description) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description == null ? "" : description;
        }

        public Experience(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Experience(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "Experience: " +
                    "startDate: " + startDate +
                    ", endDate: " + endDate +
                    ", title: " + title + '\'' +
                    ", description: " + description + '\n';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Experience experience = (Experience) o;
            return Objects.equals(startDate, experience.startDate) &&
                    Objects.equals(endDate, experience.endDate) &&
                    Objects.equals(title, experience.title) &&
                    Objects.equals(description, experience.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }
    }
}

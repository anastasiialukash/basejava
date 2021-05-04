package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData {

    public static void main(String[] args) {
        System.out.println(getResume("1111", "testName").toString());
    }

    public static Resume getResume(String uuid, String fullname) {

        Resume resume = new Resume(uuid, fullname);
        resume.addContact(ContactType.EMAIL, "mail1@ya.ru");
        resume.addContact(ContactType.PHONE, "11111");
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        resume.addSection(SectionType.EXPERIENCE, new OrganisationSection(
                new Organisation("Organisation11", "http://Organisation11.ru",
                        new Organisation.Experience(2005, Month.JANUARY, "position1", "content1"),
                        new Organisation.Experience(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        resume.addSection(SectionType.EDUCATION,
                new OrganisationSection(
                        new Organisation("Institute", "http://Organisation1.ru",
                                new Organisation.Experience(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organisation.Experience(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organisation("Organisation12", "http://Organisation12.ru",
                        new Organisation.Experience(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null))));
        resume.addSection(SectionType.EXPERIENCE,
                new OrganisationSection(new Organisation("Organisation2", "http://Organisation2.ru",
                        new Organisation.Experience(2015, Month.JANUARY, 2017, Month.APRIL, "position1", "content1"))));
        return resume;
    }
}
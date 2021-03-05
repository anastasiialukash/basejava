package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Map<ContactsType, String> contactsMap = new EnumMap<>(ContactsType.class);
        Map<SectionType, AbstractSection> sectionsMap = new EnumMap<>(SectionType.class);
        ArrayList<String> achievements = new ArrayList<>();
        ArrayList<String> qualification = new ArrayList<>();
        Organisation education1 = new Organisation(
                "Siemens AG",
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2005, 4, 1),
                null,
                "3 месяца обучения мобильным IN сетям (Берлин)"
        );

        Organisation education2 = new Organisation(
                "Luxoft",
                LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1),
                null,
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"\n"
        );

        Organisation experience1 = new Organisation(
                "Java Online Projects",
                LocalDate.of(2013, 10, 1),
                LocalDate.now(),
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок."
        );

        Organisation experience2 = new Organisation(
                "Wrike",
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                        "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
        );

        ArrayList<Organisation> educationList = new ArrayList<>();
        ArrayList<Organisation> experienceList = new ArrayList<>();

        educationList.add(education1);
        educationList.add(education2);

        experienceList.add(experience1);
        experienceList.add(experience2);

        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\"");
        achievements.add("Реализация двухфакторной аутентификации");
        achievements.add("Реализация c нуля Rich Internet Application приложения");

        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualification.add("MySQL, SQLite, MS SQL, HSQLDB");

        contactsMap.put(ContactsType.PHONE, "012345");
        contactsMap.put(ContactsType.EMAIL, "test@mail.com");
        contactsMap.put(ContactsType.SKYPE, "testSkypeID");

        sectionsMap.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, " +
                "сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        sectionsMap.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения" +
                " по Java Web и Enterprise технологиям"));
        sectionsMap.put(SectionType.ACHIEVEMENT, new ListSection(achievements));
        sectionsMap.put(SectionType.QUALIFICATIONS, new ListSection(qualification));
        sectionsMap.put(SectionType.EDUCATION, new OrganisationSection(educationList));
        sectionsMap.put(SectionType.EXPERIENCE, new OrganisationSection(experienceList));

        Resume resume = new Resume(
                "uuid000",
                "Test Name",
                contactsMap,
                sectionsMap
        );

        System.out.println(resume);
    }
}

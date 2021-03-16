package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {
        System.out.println(getResume("1111", "testName").toString());
    }

    public static Resume getResume(String uuid, String fullname) {

        Map<ContactsType, String> contactsMap = new EnumMap<>(ContactsType.class);
        Map<SectionType, AbstractSection> sectionsMap = new EnumMap<>(SectionType.class);

        List<String> achievements = new ArrayList<>();
        List<String> qualification = new ArrayList<>();

        List<Experience> edu1 = new ArrayList<>();
        Experience roleEdu1 = new Experience
                (
                        LocalDate.of(2005, 1, 1),
                        LocalDate.of(2005, 1, 1),
                        null,
                        "3 месяца обучения мобильным IN сетям (Берлин)"
                );

        edu1.add(roleEdu1);

        Organisation education1 = new Organisation(
                "Siemens AG",
                "https://new.siemens.com/ru/ru.html",
                edu1);

        List<Experience> edu2 = new ArrayList<>();
        Experience roleEdu2 = new Experience(
                LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1),
                null,
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"\n"
        );
        edu2.add(roleEdu2);

        Organisation education2 = new Organisation(
                "Luxoft",
                "https://www.luxoft-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html",
                edu2);

        List<Experience> job1 = new ArrayList<>();
        Experience role1Job1 = new Experience(
                LocalDate.of(2013, 10, 1),
                LocalDate.now(),
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок."
        );

        Experience role2Job1 = new Experience(
                LocalDate.of(2013, 10, 1),
                LocalDate.now(),
                "Разработчик",
                "Разработка проектов"
        );

        job1.add(role1Job1);
        job1.add(role2Job1);


        Organisation experience1 = new Organisation(
                "Java Online Projects",
                "https://javaops.ru/",
                job1
        );

        List<Experience> job2 = new ArrayList<>();
        Experience roleJob2 = new Experience(
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                        "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
        );

        job2.add(roleJob2);

        Organisation experience2 = new Organisation(
                "Wrike",
                "https://www.wrike.com/",
                job2
        );

        List<Organisation> educationList = new ArrayList<>();
        List<Organisation> experienceList = new ArrayList<>();

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

        return new Resume(uuid, fullname, contactsMap, sectionsMap);
    }

}

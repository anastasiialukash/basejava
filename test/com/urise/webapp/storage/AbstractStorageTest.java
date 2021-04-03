package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = new File("/Users/alukash/Documents/QA/test");
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_NEW = "new";
    private static final String UUID_DUMMY = "dummy";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_NEW;
    private static final Resume RESUME_DUMMY;

    static {
        RESUME_1 = new Resume(UUID_1, "name1");
        RESUME_2 = new Resume(UUID_2, "name2");
        RESUME_3 = new Resume(UUID_3, "name3");
        RESUME_NEW = new Resume(UUID_NEW, "nameNew");
        RESUME_DUMMY = new Resume(UUID_DUMMY, "nameDummy");

        RESUME_1.addContact(ContactsType.EMAIL, "mail1@ya.ru");
        RESUME_1.addContact(ContactsType.PHONE, "11111");
        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        RESUME_1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        RESUME_1.addSection(SectionType.EXPERIENCE, new OrganisationSection(
                new Organisation("Organisation11", "http://Organisation11.ru",
                        new Experience(2005, Month.JANUARY, "position1", "content1"),
                        new Experience(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        RESUME_1.addSection(SectionType.EDUCATION,
                new OrganisationSection(
                        new Organisation("Institute", null,
                                new Experience(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Experience(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organisation("Organisation12", "http://Organisation12.ru")));
        RESUME_2.addContact(ContactsType.SKYPE, "skype2");
        RESUME_2.addContact(ContactsType.PHONE, "22222");
        RESUME_1.addSection(SectionType.EXPERIENCE,
                new OrganisationSection(new Organisation("Organisation2", "http://Organisation2.ru",
                        new Experience(2015, Month.JANUARY, "position1", "content1"))));
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumes = storage.getAllSorted();
        Assert.assertEquals(3, resumes.size());
        Assert.assertEquals(resumes, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    @Test
    public void save() {
        storage.save(RESUME_NEW);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(RESUME_NEW, storage.get(UUID_NEW));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExists() {
        storage.save(new Resume(UUID_1, "name1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_DUMMY);
    }

    @Test
    public void update() {
        Resume updatedResume = new Resume(UUID_3, "name3");
        storage.update(RESUME_3);
        Assert.assertEquals(updatedResume.toString(), storage.get(UUID_3).toString());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_DUMMY);
    }

    @Test
    public void get() {
        Resume resume = storage.get(UUID_1);
        Assert.assertEquals(RESUME_1, resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_DUMMY);
    }
}

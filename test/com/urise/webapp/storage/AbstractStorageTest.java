package com.urise.webapp.storage;

import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_NEW = "new";
    protected static final String UUID_DUMMY = "dummy";

//    protected static final Resume RESUME_1 = new Resume(UUID_1, "name1");
//    protected static final Resume RESUME_2 = new Resume(UUID_2, "name2");
//    protected static final Resume RESUME_3 = new Resume(UUID_3, "name3");
//    protected static final Resume RESUME_NEW = new Resume(UUID_NEW, "nameNew");
//    protected static final Resume RESUME_DUMMY = new Resume(UUID_DUMMY, "nameDummy");

    protected static final Resume RESUME_1 = ResumeTestData.getResume(UUID_1, "name1");
    protected static final Resume RESUME_2 = ResumeTestData.getResume(UUID_2, "name2");
    protected static final Resume RESUME_3 = ResumeTestData.getResume(UUID_3, "name3");
    protected static final Resume RESUME_NEW = ResumeTestData.getResume(UUID_NEW, "nameNew");
    protected static final Resume RESUME_DUMMY = ResumeTestData.getResume(UUID_DUMMY, "nameDummy");

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
        Assert.assertEquals(RESUME_1, resumes.get(0));
        Assert.assertEquals(RESUME_2, resumes.get(1));
        Assert.assertEquals(RESUME_3, resumes.get(2));
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
        Assert.assertEquals(updatedResume, storage.get(UUID_3));
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

package com.urise.webapp.storage;

import com.urise.Config;
import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {

//    protected static final File STORAGE_DIR = new File("/Users/alukash/Documents/QA/test");
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_NEW = "new_uuid";
    private static final String UUID_DUMMY = "dummy";

    private static final Resume RESUME_1 = ResumeTestData.getResume(UUID_1, "name1");
    private static final Resume RESUME_2 = ResumeTestData.getResume(UUID_2, "name2");
    private static final Resume RESUME_3 = ResumeTestData.getResume(UUID_3, "name3");
    private static final Resume RESUME_NEW = ResumeTestData.getResume(UUID_NEW, "new");
    private static final Resume RESUME_DUMMY = ResumeTestData.getResume(UUID_DUMMY, "dummy");

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
        assertGet(RESUME_NEW);
//        Assert.assertEquals(RESUME_NEW, storage.get(UUID_NEW));
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
        Resume updatedResume = new Resume(UUID_1, "new_name");
        storage.update(updatedResume);
        Assert.assertEquals(updatedResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_DUMMY);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_DUMMY);
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }
}

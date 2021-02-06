package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void getStorageOverflow() {
        int uuidValue = 0;
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume(Integer.toString(uuidValue), "Test"));
                uuidValue++;
            }
        } catch (StorageException e) {
            Assert.fail("The storage is not overflowed yet");
        }
        storage.save(new Resume(Integer.toString(uuidValue + 1), "Test"));
    }
}

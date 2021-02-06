package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void replaceDeletedElement(int index) {
        storage[index] = storage[size - 1];
    }

    protected Integer getSearchKey(String uuid, String fullName) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void saveElement(Resume resume, Integer searchKey) {
        if (size == storage.length) {
            throw new StorageException("The storage is overflowed", resume.getUuid());
        } else {
            insertElement(resume, searchKey);
            size++;
        }
    }

    @Override
    protected void updateElement(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    protected Resume getElement(Integer index) {
        return storage[index];
    }

    @Override
    protected void deleteElement(Integer index) {
        replaceDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean elementExists(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume[] getAllElements() {
        return Arrays.stream(storage)
                .filter(Objects::nonNull)
                .toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void replaceDeletedElement(int index);
}

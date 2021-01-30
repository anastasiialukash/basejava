package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void saveElement(Resume resume, Object searchParameter) {
        if (size == storage.length) {
            throw new StorageException("The storage is overflowed", resume.getUuid());
        } else if (getSearchParameter(resume.getUuid()) >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertElement(resume, (Integer) searchParameter);
            size++;
        }
    }

    @Override
    protected void updateElement(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected Resume getElement(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected void deleteElement(Object index) {
        replaceDeletedElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean elementExists(Object searchParameter) {
        return (Integer) searchParameter >= 0;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray((Resume[]::new));
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract Integer getSearchParameter(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void replaceDeletedElement(int index);
}

package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void delete(String uuid) {
        int index = getElementIndex(uuid);
        if (index >= 0) {
            deleteElement(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume get(String uuid) {
        int index = getElementIndex(uuid);
        if (getElementIndex(uuid) >= 0) {
            return getResume(index);
        }
        throw new NotExistStorageException(uuid);
    }

    public void update(Resume resume) {
        int index = getElementIndex(resume.getUuid());
        if (index >= 0) {
            updateResume(resume, index);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract int getElementIndex(String uuid);

    protected abstract void deleteElement(int index);

    protected abstract Resume getResume(int index);

    protected abstract void updateResume(Resume resume, int index);
}

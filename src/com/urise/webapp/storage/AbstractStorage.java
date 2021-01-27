package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        int index = getElementIndex(resume.getUuid());
        saveResume(resume, index);
    }

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

    public int size() {
        return getSize();
    }

    public Resume[] getAll() {
        return getAllResume();
    }

    public void clear() {
        clearStorage();
    }

    protected abstract int getElementIndex(String uuid);

    protected abstract void deleteElement(int index);

    protected abstract Resume getResume(int index);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract int getSize();

    protected abstract Resume[] getAllResume();

    protected abstract void clearStorage();
}

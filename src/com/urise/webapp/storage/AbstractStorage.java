package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator
            .comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    public void save(Resume resume) {
        Object searchKey = getSearchKeyForNotExistedElement(resume.getUuid(), resume.getFullName());
        saveElement(resume, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKeyForExistedElement(uuid, "");
        deleteElement(searchKey);
    }

    public Resume get(String uuid, String fullName) {
        Object searchKey = getSearchKeyForExistedElement(uuid, fullName);
        return getElement(searchKey);
    }

    public void update(Resume resume) {
        Object searchKey = getSearchKeyForExistedElement(resume.getUuid(), resume.getFullName());
        updateElement(resume, searchKey);
    }

    public Object getSearchKeyForExistedElement(String uuid, String fullName) {
        Object searchKey = getSearchKey(uuid, fullName);
        if (!elementExists(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public Object getSearchKeyForNotExistedElement(String uuid, String fullName) {
        Object searchKey = getSearchKey(uuid, fullName);
        if (elementExists(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Object getSearchKey(String uuid, String fullName);

    protected abstract boolean elementExists(Object searchKey);

    protected abstract void saveElement(Resume resume, Object searchKey);

    protected abstract void updateElement(Resume resume, Object searchKey);

    protected abstract Resume getElement(Object searchKey);

    protected abstract void deleteElement(Object searchKey);

}

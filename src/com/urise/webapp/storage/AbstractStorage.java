package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        Object searchParameter = getSearchParameterForNotExistedElement(resume.getUuid());
        saveElement(resume, searchParameter);
    }

    public void delete(String uuid) {
        Object searchParameter = getSearchParameterForExistedElement(uuid);
        deleteElement(searchParameter);
    }

    public Resume get(String uuid) {
        Object searchParameter = getSearchParameterForExistedElement(uuid);
        return getElement(searchParameter);
    }

    public void update(Resume resume) {
        Object searchParameter = getSearchParameterForExistedElement(resume.getUuid());
        updateElement(resume, searchParameter);
    }

    public Object getSearchParameterForExistedElement(String uuid) {
        Object searchParameter = getSearchParameter(uuid);
        if (!elementExists(searchParameter)) {
            throw new NotExistStorageException(uuid);
        }
        return searchParameter;
    }

    public Object getSearchParameterForNotExistedElement(String uuid) {
        Object searchParameter = getSearchParameter(uuid);
        if (elementExists(searchParameter)) {
            throw new ExistStorageException(uuid);
        }
        return searchParameter;
    }

    protected abstract Object getSearchParameter(String uuid);

    protected abstract boolean elementExists(Object searchParameter);

    protected abstract void saveElement(Resume resume, Object searchParameter);

    protected abstract void updateElement(Resume resume, Object searchParameter);

    protected abstract Resume getElement(Object searchParameter);

    protected abstract void deleteElement(Object searchParameter);

}

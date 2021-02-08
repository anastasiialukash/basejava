package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator
            .comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    public void save(Resume resume) {
        Object searchKey = getSearchKeyForNotExistedElement(resume.getUuid());
        saveElement(resume, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKeyForExistedElement(uuid);
        deleteElement(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchKeyForExistedElement(uuid);
        return getElement(searchKey);
    }

    public void update(Resume resume) {
        Object searchKey = getSearchKeyForExistedElement(resume.getUuid());
        updateElement(resume, searchKey);
    }

    private Object getSearchKeyForExistedElement(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!elementExists(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchKeyForNotExistedElement(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (elementExists(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.stream(getAllElements())
                .sorted(RESUME_COMPARATOR)
                .collect(Collectors.toList());
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean elementExists(Object searchKey);

    protected abstract void saveElement(Resume resume, Object searchKey);

    protected abstract void updateElement(Resume resume, Object searchKey);

    protected abstract Resume getElement(Object searchKey);

    protected abstract void deleteElement(Object searchKey);

    protected abstract Resume[] getAllElements();

}

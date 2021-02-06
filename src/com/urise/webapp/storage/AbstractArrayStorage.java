package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void saveElement(Resume resume, Object searchKey) {
        if (size == storage.length) {
            throw new StorageException("The storage is overflowed", resume.getUuid());
        } else {
            insertElement(resume, (Integer) searchKey);
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
    protected boolean elementExists(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.stream(storage)
                .filter(Objects::nonNull)
                .sorted(RESUME_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract Integer getSearchKey(String uuid, String fullName);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void replaceDeletedElement(int index);
}

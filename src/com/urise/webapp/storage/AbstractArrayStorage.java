package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public Resume get(String uuid) {
        int index = getElementIndex(uuid);
        if (getElementIndex(uuid) >= 0) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " is not found");
        return null;
    }

    public void update(Resume resume) {
        int index = getElementIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " is not found");
        }
    }

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray((Resume[]::new));
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected abstract int getElementIndex(String uuid);

    public abstract void save(Resume resume);

    public abstract void delete(String uuid);
}

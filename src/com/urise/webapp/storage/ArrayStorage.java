package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("The storage is overflowed");
        } else if (getElementIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume " + resume.getUuid() + " already exists");
        } else {
            storage[size] = resume;
            size++;
        }
    }


    public void update(String currentUuid, String updatedUuid) {
        int index = getElementIndex(currentUuid);
        if (index >= 0) {
            storage[index].setUuid(updatedUuid);
        } else {
            System.out.println("Resume " + currentUuid + " is not found");
        }
    }

    public Resume get(String uuid) {
        int index = getElementIndex(uuid);
        if (getElementIndex(uuid) >= 0) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " is not found");
        return null;
    }

    public void delete(String uuid) {
        int index = getElementIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + " is not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    public int size() {
        return size;
    }

    public int getElementIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

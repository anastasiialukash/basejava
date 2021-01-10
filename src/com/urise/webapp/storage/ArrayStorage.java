package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;
    private int uuidIndex = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("The storage is overflowed");
        } else {
            if (isPresent(r.uuid)) {
                System.out.println("Resume " + r.uuid + " already exists");
            } else {
                storage[size] = r;
                size++;
            }
        }
    }

    public void update(String currentUuid, String updatedUuid) {
        if (isPresent(currentUuid)) {
            storage[uuidIndex].uuid = updatedUuid;
        } else {
            System.out.println("Resume " + currentUuid + " is not found");
        }
    }

    public Resume get(String uuid) {
        if (isPresent(uuid)) {
            return storage[uuidIndex];
        } else {
            System.out.println("Resume " + uuid + " is not found");
        }
        return null;
    }

    public void delete(String uuid) {
        if (isPresent(uuid)) {
            storage[uuidIndex] = storage[size - 1];
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

    public boolean isPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                uuidIndex = i;
                return true;
            }
        }
        return false;
    }
}
